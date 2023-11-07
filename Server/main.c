#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"

SOCKET checkNewConnection(SOCKET serverSocket, SOCKET clientSocket, struct sockaddr_in clientAddr, int clientAddrLen);
char * recieveFromClient(const SOCKET * newClientSocket);
void sendDataToClient(SOCKET * clientSocket, const char * message);
int main() {
    WSADATA wsaData;
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);

    // Initialize Winsock
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        perror("WSAStartup failed");
        return 1;
    }

    // Create a socket
    serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket == INVALID_SOCKET) {
        perror("Socket creation failed");
        WSACleanup();
        return 1;
    }

    // Bind the socket to an address and port
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(12345); // You can choose a port number
    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) == SOCKET_ERROR) {
        perror("Bind failed");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }
    SOCKET * newClientSocket = (SOCKET *)malloc(sizeof(SOCKET));
    *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);

    if (*newClientSocket == SOCKET_ERROR || *newClientSocket == INVALID_SOCKET) {
        perror("Connection failed");
        closesocket(serverSocket);
        WSACleanup();
        return 0;
    }

    char * messageFromClient = recieveFromClient(newClientSocket);
    printf("From client: %s \n", messageFromClient);
    free(messageFromClient);

    printf("Mensaje a enviar al cliente: \n");
    char * message;
    scanf("%s", message);
    sendDataToClient(newClientSocket, message);

    // Close the sockets when done
    closesocket(*newClientSocket);
    closesocket(serverSocket);
    WSACleanup();

    free(newClientSocket);

    return 0;
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
        printf("Buffer here: %s \n", buffer);
        return buffer;
    } else if (bytesRead == 0) {
        return "disconnected";
    }
    return "failed";
}

void sendDataToClient(SOCKET * clientSocket, const char * message){
    send(*clientSocket, message, strlen(message), 0);
}