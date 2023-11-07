#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
#include "ServerSocket/SocketManagement.h"

int main() {
    WSADATA * wsaData = (WSADATA*) malloc(sizeof(WSADATA));
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);

    serverSocket = startUpServer(serverSocket,wsaData, serverAddr);

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
    char * message = (char *)malloc(sizeof(char));
    scanf("%s", message);
    sendDataToClient(newClientSocket, message);
    free(message);
    
    // Close the sockets when done
    closesocket(*newClientSocket);
    closesocket(serverSocket);
    WSACleanup();

    free(newClientSocket);
    free(wsaData);

    return 0;
}







