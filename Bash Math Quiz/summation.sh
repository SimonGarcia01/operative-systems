#!/bin/bash

echo -e "\nSolve the next sum: "

declare -r max=101

declare -i num1=$((RANDOM % max))
declare -i num2=$((RANDOM % max))

declare -i real_answer=$((num1 + num2))

echo "What is $num1 + $num2?"

declare -i user_answer
declare -i attempts=0

while (( $attempts < 3 ))
do
    echo -e -n "\nYour answer: "; read user_answer
    
    if (( $user_answer == $real_answer ))
        then
            echo -e "\nThat's right!"
            break
        else
            echo -e "\nIncorrect. Try again, you can do it!"
            echo -e "You have $((2 - attempts)) attempts left."

            ((attempts++))

            if (( $attempts == 3 ))
                then
                    echo -e "\nSorry, the correct answer is $real_answer."
            fi
    fi
done