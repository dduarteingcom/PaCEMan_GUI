//
// Created by alexi on 7/11/2023.
//

#ifndef SERVER_ACTIONSMANAGEMENT_H
#define SERVER_ACTIONSMANAGEMENT_H

#include "../UsersList.h"
#include <stdio.h>
#include <malloc.h>
#include <string.h>

void createObject(struct userList * list, int operation);
void createGhostCommand(struct user * userTargeted);
void createPillCommand(struct user * userTargeted);
void createFruitCommand(struct user * userTargeted);
void changeGhostSpeedCommand(struct user * userTargeted);
void queueCommand(struct user * userTargeted, char * message);
#endif //SERVER_ACTIONSMANAGEMENT_H
