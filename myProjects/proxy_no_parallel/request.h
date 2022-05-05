#include <string>

class Request
{
private:
    std::string version;
    std::string operation;
    std::string host;
    std::string dataName;

    void parse(char *request, size_t size)
    {
        std::cout << "startParsing : \n";
        int j = 0;
        std::cout << "operation : \n";
        for (int i = 0; i < size; ++i)
        {
            std::cout << request[i] <<"\n";
            if (request[i] == ' ' || request[i] == '\n' || request[i] == '\r')
            {
                j = i;
                std::cout << operation;
                break;
            }
            else
            {
                operation = operation + request[i];
            }
        }

        for (int i = j; i < size; ++i)
        {
            if (request[i] != ' ' && request[i] != '\n' && request[i] != '\r')
            {
                j = i;
                break;
            }
        }

        std::cout << "dataName : \n";
        for (int i = j; i < size; ++i)
        {
            std::cout << request[i] <<"\n";
            if (request[i] == ' ' || request[i] == '\n' || request[i] == '\r')
            {
                j = i;
                break;
            }
            else
            {
                dataName = dataName + request[i];
            }
        }

        for (int i = j; i < size; ++i)
        {
            if (request[i] != ' ' && request[i] != '\n' && request[i] != '\r')
            {
                j = i;
                break;
            }
        }

        std::cout << "version : \n";
        for (int i = j; i < size; ++i)
        {
            if (request[i] == ' ' || request[i] == '\n' || request[i] == '\r')
            {
                j = i;
                break;
            }
            else
            {
                std::cout << request[i] <<"\n";
                version = version + request[i];
            }
        }

        if (dataName[0] != 'h' && dataName[1] != 't' && dataName[2] != 't' && dataName[3] != 'p' && dataName[4] != ':') {
            for (int i = 0; i < dataName.size(); ++i) {
                if (dataName[i] == '/') {
                    break;
                }
                host = host + dataName[i];
            }
        } else {
            for (int i = 7; i < dataName.size(); ++i) {
                if (dataName[i] == '/') {
                    break;
                }
                host = host + dataName[i];
            }
        }
    }

public:
    Request(char* request, size_t size) {
        parse(request, size);
    }

    std::string getDataName() {
        return dataName;
    }

    std::string getHost() {
        return host;
    }

    std::string getVersion() {
        return version;
    }

    std::string getOperation() {
        return operation;
    }
};