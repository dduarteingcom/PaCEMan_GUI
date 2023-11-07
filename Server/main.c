#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
#include "ServerSocket/SocketManagement.h"

int displayMainMenu(SOCKET * clientSocket, struct userList * clientList);
void checkClientData(struct userList * list, char * messageFromUser);
void trim(char * dest, char * src);

int main() {
    WSADATA * wsaData = (WSADATA*) malloc(sizeof(WSADATA));
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);
    int optionSelected = -1;
    struct userList * clientList = initializeList();

    serverSocket = startUpServer(serverSocket,wsaData, serverAddr);

    SOCKET * newClientSocket = (SOCKET *)malloc(sizeof(SOCKET));
    *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);

    printf("Bienvenido al sistema maestro de PaCE man. A continuacion, se le mostrara un menu de acciones: \n");

    while(optionSelected != 0){
        optionSelected = displayMainMenu(newClientSocket, clientList);
        closesocket(*newClientSocket);
        *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
    }


    // Close the sockets when done
    closesocket(*newClientSocket);
    closesocket(serverSocket);
    WSACleanup();

    free(newClientSocket);
    free(wsaData);
    free(clientList);
    return 0;
}

int displayMainMenu(SOCKET * clientSocket, struct userList * clientList){
    int * option = (int *)malloc(sizeof(int));
    printf("___________________________\n");
    printf("0 - Terminar conexion \n 1 - Enviar mensaje a cliente \n");
    printf("___________________________\n");
    scanf("%d", option);
    char * clientName;
    switch(*option){
        case 0:
            printf("Adios :) \n");
            free(option);
            return 0;
        case 1:
            clientName = recieveFromClient(clientSocket);
            checkClientData(clientList, clientName);
            printf("Mensaje a enviar al cliente: \n");
            char * message = (char *)malloc(sizeof(char));
            scanf("%s", message);
            sendDataToClient(clientSocket, message);
            free(message);
            free(option);
            return 1;
    }
    free(option);
    return -1;
}

void checkClientData(struct userList * list, char * messageFromUser){
    trim(messageFromUser, messageFromUser);
    struct user * client = findUserByCode(list,messageFromUser);
    if(client == NULL){
        struct user * newClient = createUser(messageFromUser);
        addNodeLast(list, newClient);
    }else{
        printf("Client registered \n");
    }
    printUserList(list);
}

void trim (char *dest, char *src)
{
    if (!src || !dest)
        return;

    int len = strlen (src);

    if (!len) {
        *dest = '\0';
        return;
    }
    char *ptr = src + len - 1;

    // remove trailing whitespace
    while (ptr > src) {
        if (!isspace (*ptr))
            break;
        ptr--;
    }

    ptr++;

    char *q;
    // remove leading whitespace
    for (q = src; (q < ptr && isspace (*q)); q++)
        ;

    while (q < ptr)
        *dest++ = *q++;

    *dest = '\0';
}






