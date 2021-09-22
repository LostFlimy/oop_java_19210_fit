import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ClientHandler implements Runnable{
    private static final int LONG_IN_BYTES_SIZE = 8;
    public static final int INT_IN_BYTES_SIZE = 4;
    private static final int FILENAME_BUFFER_SIZE = 4096 * 4;
    private static final int DELAY = 1 * 1000;

    public int CAN_NOT_UPLOAD = -1;
    public int I_CAN_IT_UPLOAD = 1;
    public int SUCCESS_UPLOAD = 2;

    private final Socket socket;
    private File storage;
    private final DataInputStream in;
    private final DataOutputStream out;

    private final ByteBuffer sizesBuffer = ByteBuffer.wrap(new byte[LONG_IN_BYTES_SIZE]);
    private final byte[] fileNameBuffer = new byte[FILENAME_BUFFER_SIZE];
    private final byte[] fileBuffer = new byte[256];
    public ClientHandler(Socket socket, String filepath) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        storage = new File(filepath);
    }
    @Override
    public void run() {
        while(true) {
            System.out.println("Connected " + socket.getInetAddress());
            long storageSize = storage.length();
            System.out.println("Try to store file size...");
            long fileSize = 0;
            int fileNameSize;

            try {
                fileSize = readFileSize();
                if (fileSize == 0) {
                    socket.close();
                    return;
                }
            } catch (IOException ex) {
                System.out.println("Can not upload fileSize");
                ex.printStackTrace();
            }


            try {
                out.write(I_CAN_IT_UPLOAD);
                fileNameSize = readFileNameSize();
                if(uploadFile(new File(storage, uploadFileName(fileNameSize)), fileSize)) {
                    System.out.println("File from " + socket.getInetAddress().getHostAddress() + " was stored succesfull");
                    out.write(SUCCESS_UPLOAD);
                    socket.close();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Unknown error");
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            }
        }
    }

    private boolean uploadFile(File uploadFile, long fileSize) {
        System.out.println("Uploading : " + uploadFile.getName() + " from " + socket.getInetAddress().getHostAddress());

        long fullLen = 0;
        long curTime = System.currentTimeMillis();
        long beginTime = curTime;
        long nextUpdateTime = curTime + DELAY;
        long delayLen = 0;
        int len = 0;

        try(FileOutputStream outputStream = new FileOutputStream(uploadFile);) {
            do {
                len = in.read(fileBuffer, 0, fileBuffer.length);
                if(len < 0) {
                    System.out.println("Can not upload File");
                    socket.close();
                    return false;
                }
                fullLen += len;
                delayLen += len;
                outputStream.write(fileBuffer, 0, len);

                long time = System.currentTimeMillis();

                if(time >= nextUpdateTime) {
                    System.out.println("Current " + getSpeed(delayLen, DELAY/1000));
                    curTime = time;
                    delayLen = 0;
                    nextUpdateTime = curTime + DELAY;
                }
            } while (fullLen < fileSize);

            System.out.println("Average " + getSpeed(fullLen, (curTime - beginTime) / 1000));

        } catch(IOException ex) {
            try{
                socket.close();
            } catch (IOException e){ }
            System.out.println("Unknown error");
        }
        return true;
    }

    private String getSpeed(long delayLen, long delay) {
        System.out.println();
        long speedInB = (delayLen / delay) % 1024;
        long speedInKB = (delayLen / (delay  * 1024)) % 1024;
        long speedInMB = (delayLen / (delay * 1024 * 1024));
        if(speedInMB != 0) {
            return new String("speed is " + speedInMB + " MB/sec");
        } else {
            if(speedInKB != 0) {
                return new String("speed is " + speedInKB + "KB/sec");
            } else {
                return new String("speed is " + speedInB + "B/sec");
            }
        }
    }

    private String uploadFileName(int fileNameSize) throws IOException{
        readAtLeastBytes(fileNameBuffer, 0, fileNameSize);
        return new String(fileNameBuffer, 0, fileNameSize, Charset.forName("UTF-8"));
    }

    private int readFileNameSize() throws IOException {
        sizesBuffer.clear();
        readAtLeastBytes(sizesBuffer.array(), 0, INT_IN_BYTES_SIZE);
        return sizesBuffer.getInt();
    }

    private long readFileSize() throws IOException {
        sizesBuffer.clear();
        readAtLeastBytes(sizesBuffer.array(), 0, LONG_IN_BYTES_SIZE);
        return sizesBuffer.getLong();
    }

    private void readAtLeastBytes(byte[] buffer, int offset, int required) throws IOException {
        int len = 0;
        while (len < required) {
            int count = in.read(buffer, offset + len, required - len);
            if (count == -1)
                throw new IOException("Cannot read bytes");
            len += count;
        }
    }
}
