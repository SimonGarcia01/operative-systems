#!/bin/bash

# --------------------------- FUNCTIONS ---------------------------
function main_menu()
{
    echo -e "\nMathematics Quiz Main Menu:"
    echo "1) Summation problems"
    echo "2) Substraction problems"
    echo "3) Multiplication problems"
    echo "4) Division problems"
    echo "9) Exit"

    echo "Please select an option: "
}

# --------------------------- MAIN PROGRAM  ---------------------------

while true
do
    main_menu
    read option
    case $option in
        1) ./summation.sh;;
        2) ./subtraction.sh;;
        3) ./multiplication.sh;;
        4) ./division.sh;;
        9) echo "Exiting..."; break;;
        *) echo "Invalid option, please try again.";;
    esac
done