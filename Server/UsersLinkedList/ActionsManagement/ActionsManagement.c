//
// Created by alexi on 7/11/2023.
//


#include "ActionsManagement.h"



void createObject(struct userList * list, int operation){
    printf("Id del usuario para crear el objeto: \n");
    char * userCode = (char *)malloc(sizeof(char *));
    scanf("%s", userCode);
    struct user * userTargeted = findUserByCode(list, userCode);
    if(userTargeted == NULL){
        printf("Usuario no existente \n");
    }else{
        switch(operation){
            case 1:
                createGhostCommand(userTargeted);
                break;
            case 2:
                createPillCommand(userTargeted);
                break;
            case 3:
                createFruitCommand(userTargeted);
                break;
            case 4:
                changeGhostSpeedCommand(userTargeted);
                break;
            default:
                break;
        }
    }
    free(userCode);
}

void createGhostCommand(struct user * userTargeted){
    queueCommand(userTargeted, "ghost_");
}

void createPillCommand(struct user * userTargeted){
    char * fila = (char *)malloc(sizeof(char ));
    char * columna = (char *)malloc(sizeof(char ));
    char * message = strdup("pill_");
    printf("Escriba la fila para aparecer: \n");
    scanf("%s", fila);
    printf("Escriba la columna para aparecer: \n");
    scanf("%s", columna);

    strcat(columna,"_"); //columna + "_"
    strcat(columna, fila); //columna + "_" + fila
    strcat(message, columna); // operacion_columna_fila
    free(fila);
    free(columna);
    queueCommand(userTargeted, message);
}

void createFruitCommand(struct user * userTargeted){
    char * points = (char *)malloc(sizeof(char *));
    char * message = strdup("fruit_");
    printf("Escriba la cantidad de puntos a designar a la fruta: \n");
    scanf("%s", points);
    strcat(message, points);
    free(points);
    queueCommand(userTargeted, message);
}

void changeGhostSpeedCommand(struct user * userTargeted){
    char * speed = (char *)malloc(sizeof(char *));
    char * message = strdup("speed_");
    printf("Escriba la velocidad a setear: \n");
    scanf("%s", speed);
    strcat(message, speed);
    free(speed);
    queueCommand(userTargeted, message);
}

void queueCommand(struct user * userTargeted, char * message){
    printf("En seguida se configurara el comando... \n");
    addCommandLast(userTargeted->commands, createCommand(message));
    printf("Listo. El comando se ha salvado. \n");
}