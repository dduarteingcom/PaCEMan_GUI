//
// Created by alexi on 6/11/2023.
//

#include "SocketManagement.h"

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
        perror("Bind failed");
        closesocket(serverSocket);
        WSACleanup();
        exit(0);
    }
    return serverSocket;
}

SOCKET checkNewConnection(SOCKET serverSocket, SOCKET clientSocket, struct sockaddr_in clientAddr, int clientAddrLen){

    // Listen for incoming connections
    if (listen(serverSocket, 5) == SOCKET_ERROR) {
        perror("Listen failed");
        closesocket(serverSocket);
        WSACleanup();
        return SOCKET_ERROR;
    }

    printf("Servidor corriendo\n");

    // Accept incoming connections


    clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &clientAddrLen);
    if (clientSocket == INVALID_SOCKET) {
        perror("Accept failed");
        closesocket(serverSocket);
        WSACleanup();
        return INVALID_SOCKET;
    }

    printf("Nueva conexion al servidor\n");

    return clientSocket;
}

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

void sendDataToClient(const SOCKET * clientSocket, const char * message){
    send(*clientSocket, message, strlen(message), 0);
}