#include <stdio.h>
#include <winsock2.h>
#include "UsersLinkedList/UsersList.h"
#include "ServerSocket/SocketManagement.h"
#include <pthread.h>

void trim(char * dest, char * src);
void listTestFunction();
void comListTestFunction();
void * ServerThread(void *vargp);
int mainControlMenu(struct userList * clientList);
struct userList * clientList;

int main() {
    //listTestFunction(); //Eliminar al final!!!!!!!!!
    //comListTestFunction(); //Eliminar al final!!!!!!!!!
    clientList = initializeList();
    pthread_t tid;
    pthread_create(&tid, NULL, ServerThread, (void *)clientList);
    int option = -1;
    while(option != 0){
        option = mainControlMenu(clientList);
    }
    pthread_exit(NULL);
    free(clientList);
    return 0;
}

void * ServerThread(void *vargp){

    WSADATA * wsaData = (WSADATA*) malloc(sizeof(WSADATA));
    SOCKET serverSocket, clientSocket;
    struct sockaddr_in serverAddr, clientAddr;
    int clientAddrLen = sizeof(clientAddr);

    serverSocket = startUpServer(serverSocket,wsaData, serverAddr);

    SOCKET * newClientSocket = (SOCKET *)malloc(sizeof(SOCKET));
    *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
    char * messageFromClient;

    while(1){
        messageFromClient = recieveFromClient(newClientSocket);
        checkAndUpdateUserInfo(clientList, messageFromClient);
        //printUserList(clientList);
        sendDataToClient(newClientSocket, "Lorem_ipsum");
        closesocket(*newClientSocket);
        *newClientSocket = checkNewConnection(serverSocket, clientSocket, clientAddr, clientAddrLen);
    }
}

int mainControlMenu(struct userList * clientList){
    int * option = (int *)malloc(sizeof(int));
    printf("Opcion a ejecutar: \n 0 - Salir \n 1 - Crear fantasma \n");
    scanf("%d", option);
    switch(*option){
        case 0:
            printf("Adios :(");
            free(option);
            return 0;
        case 1:
            printUserList(clientList);
            return 1;
    }
    return -1;
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

void listTestFunction(){
    struct userList * clientList = initializeList();
    struct user * user1 = createUser("Alvarado");
    struct user * user2 = createUser("Alfredo");
    struct user * user3 = createUser("Alexis");
    struct user * user4 = createUser("Alivio");

    addNodeLast(clientList, user1);
    addNodeLast(clientList, user2);
    addNodeLast(clientList, user3);
    addNodeLast(clientList, user4);

    printUserList(clientList);

    addCommandLast(user1->commands, createCommand("create_1_2"));

    addCommandLast(user2->commands, createCommand("destroy_5_6"));
    addCommandLast(user2->commands, createCommand("create_12_45"));
    addCommandLast(user2->commands, createCommand("ghost"));

    addCommandLast(user3->commands, createCommand("create_42_69"));
    addCommandLast(user3->commands, createCommand("speed_45"));

    printUserList(clientList);

    deleteHeadCommand(user2->commands);
    deleteHeadCommand(user2->commands);

    deleteHeadCommand(user1->commands);

    printUserList(clientList);


    //checkAndUpdateUserInfo(clientList, "Alexis_1_2_3_4");
    free(clientList);
    exit(0);
}

void comListTestFunction(){
    struct commandList * list = initializeCommandList();
    struct command * comm1 = createCommand("Alexis_1_2");
    struct command * comm2 = createCommand("Alvaro_1_2");
    struct command * comm3 = createCommand("Francisco_create_5_4");
    struct command * comm4 = createCommand("Marta_set_4_2");

    addCommandLast(list, comm1);
    addCommandLast(list, comm2);
    addCommandLast(list, comm3);
    addCommandLast(list, comm4);

    printCommandList(list);
    deleteHeadCommand(list);
    printCommandList(list);
    deleteHeadCommand(list);
    printCommandList(list);
    deleteHeadCommand(list);
    printCommandList(list);
    deleteHeadCommand(list);
    printCommandList(list);

    free(list);
    exit(0);
}