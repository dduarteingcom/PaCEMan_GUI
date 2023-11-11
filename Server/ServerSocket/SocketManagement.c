//
// Created by alexi on 6/11/2023.
//

#include "SocketManagement.h"
/**
 * @brief Starts up the server, setting its socket port and other variables.
 * @param serverSocket socket structure to connect
 * @param wsaData
 * @param serverAddr
 * @return
 */
SOCKET startUpServer(SOCKET serverSocket, WSADATA * wsaData, struct sockaddr_in serverAddr){
    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), wsaData) != 0) {
        perror("WSAStartup failed");
        exit(0);
    }
    serverSocket = socket(AF_INET, SOCK_STREAM, 0);

    if (serverSocket == INVALID_SOCKET) {
        perror("Socket creation failed");
        WSACleanup();
        exit(0);
    }

    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(12345); // You can choose a port number
    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        closingProtocol(serverSocket, "Bind failed");
    }
    return serverSocket;
}

/**
 * @brief checks for new connections available.
 * @param serverSocket socket structure to connect
 * @param clientSocket client socket structure to connect
 * @param clientAddr
 * @param clientAddrLen
 * @return
 */
SOCKET checkNewConnection(SOCKET serverSocket, SOCKET clientSocket, struct sockaddr_in clientAddr, int clientAddrLen){

    // Listen for incoming connections
    if (listen(serverSocket, 5) == SOCKET_ERROR) {
        closingProtocol(serverSocket, "Listen failed");
    }
    // Accept incoming connections
    clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &clientAddrLen);
    if (clientSocket == INVALID_SOCKET) {
        closingProtocol(serverSocket, "Accept refused");
    }
    return clientSocket;
}
/**
 * @brief recieves a message from the client
 * @param clientSocket socket structure to connect
 * @return message recieved
 */
char * recieveFromClient(const SOCKET * clientSocket){
    char * buffer = (char *)malloc(1024);
    int bytesRead = recv(*clientSocket, buffer, 1024, 0);
    if (bytesRead > 0) { //Se ha
        // recibido un mensaje al servidor
        buffer[bytesRead] = '\0'; //Mensaje recibido
        return buffer;
    } else if (bytesRead == 0) {
        return "disconnected";
    }
    return "failed";
}

/**
 * @brief Send a string to the client through the sockets
 * @param clientSocket client socket structure to connect
 * @param message message to send to the client
 */
void sendDataToClient(const SOCKET * clientSocket, const char * message){
    send(*clientSocket, message, strlen(message), 0);
}

/**
 * @brief Small closing protocol (in case a socket needs to be closed)
 * @param serverSocket socket structure to connect
 * @param errorMessage error to be desplay
 */
void closingProtocol(SOCKET serverSocket, char * errorMessage){
    perror(errorMessage);
    closesocket(serverSocket);
    WSACleanup();
    exit(0);
}

