#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
int main() {

    struct user * user0 = createUser(0);
    struct user * user1 = createUser(1);
    struct user * user2 = createUser(2);
    struct user * user3 = createUser(3);
    struct userList * list = initializeList();

    printUserList(list);

    addNodeLast(list, user0);
    addNodeLast(list, user1);

    printUserList(list);

    addNodeLast(list, user2);
    addNodeLast(list, user3);

    printUserList(list);

    printf("Found node: %d \n", *(findUserByCode(list, 2)->userCode));
    printf("Found node: %d \n", *(findUserByIndex(list, 3)->userCode));

    /*
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

    // Listen for incoming connections
    if (listen(serverSocket, 5) == SOCKET_ERROR) {
        perror("Listen failed");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    printf("Servidor corriendo\n");

    // Accept incoming connections
    clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &clientAddrLen);
    if (clientSocket == INVALID_SOCKET) {
        perror("Accept failed");
        closesocket(serverSocket);
        WSACleanup();
        return 1;
    }

    printf("Nueva conexion al servidor\n");

    char buffer[1024];
    int bytesRead = recv(clientSocket, buffer, sizeof(buffer), 0);

    if (bytesRead > 0) { //Se ha recibido un mensaje al servidor
        buffer[bytesRead] = '\0'; //Mensaje recibido
        printf("Mensaje recibido: %s\n", buffer);
    } else if (bytesRead == 0) {
        printf("Client disconnected.\n");
    } else {
        perror("Receive failed");
    }


    const char* message = "Hello from C Server!"; //Enviar mensaje al cliente
    send(clientSocket, message, strlen(message), 0);
    

    // Close the sockets when done
    closesocket(clientSocket);
    closesocket(serverSocket);
    WSACleanup();
    */
    return 0;
}
