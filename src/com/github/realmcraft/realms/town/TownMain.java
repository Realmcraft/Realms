package com.github.realmcraft.realms.town;
//Town population - size (hamlet, metropolis, etc.)


// event listeners (commands issued)
   //listen for /t spawn [name]
      //lookup town "name" in data - calls data getter on town object
      //if name does not exist - tell player invalid town name and return (calls chat)
      //else lookup coordinates of town spawn in town name
           //check permissions of plot specified by coordinates of returned town spawn calls plot object to get perms
           //if permissions disallow outsiders from entering - tell player they cannot go there b/c perms and return
           //else if player doesn't have enough money - tell player they can't afford it and return
                 //else charge the player money (ensure transaction success) and teleport them to those coordinates (ensure teleport sucess if possible - display "welcome" message) calls teleport functions
     //(Test case for /t spawn name)
     //Test 1: valid town name (check welcome message)
     //Test 2: Invalid town name - should return status indicating invalid town name
     //Test 3: no permissions - add town for player X at coord X,Z, set perm to no access, /t spawn player Y - check return
     //Test 4: no money - use NPC X which has no money, /t spawn city for player X - check return
