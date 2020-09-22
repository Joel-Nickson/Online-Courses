package main

import (
	"fmt"
  "strings"
  "os"
  "bufio"
)

type Animal interface {
	Eat()
	Speak()
	Move()
}

type Cow struct{}
func (cow Cow) Eat() {
	fmt.Println("grass")
}
func (cow Cow) Move() {
	fmt.Println("walk")
}
func (cow Cow) Speak() {
	fmt.Println("moo")
}

type Bird struct{}
func (bird Bird) Eat() {
	fmt.Println("worms")
}
func (bird Bird) Move() {
	fmt.Println("fly")
}
func (bird Bird) Speak() {
	fmt.Println("peep")
}

type Snake struct{}
func (snake Snake) Eat() {
	fmt.Println("mice")
}
func (snake Snake) Move() {
	fmt.Println("slither")
}
func (snake Snake) Speak() {
	fmt.Println("hsss")
}

func main() {
  //fmt.Println("x ==> exit")
	var req ,s1 ,s2 string
	m := make(map[string]Animal)
	c := Cow{}
	b := Bird{}
	s := Snake{}

	for {
		fmt.Print(">")
		//fmt.Scan(&req,&s1,&s2)
    
	    input:= bufio.NewScanner(os.Stdin)
	    input.Scan()
	    line := strings.TrimLeft(strings.ToLower(input.Text())," ")
	    words :=strings.Fields(line)


		if strings.ToLower(words[0])=="x"{
		    break
	    }
	    if len(words)!=3{
		    fmt.Print("Not enough arguments")
		    fmt.Println(" Or maybe you typed it wrong\njust type \"n==>newanimal\" & \"q==>query\" or x==>exit")
		    continue
	    }
	    req=words[0]
	    s1=words[1]
	    s2=words[2]
		        

		if (req[0:1] == "n") {
			if s1 == "cow" {
				m[s1+s2] = c
			} else if s1 == "bird" {
				m[s1+s2] = b
			} else if s1 == "snake" {
				m[s1+s2] = s
			} else {
				fmt.Println(" Try again")
				continue
			}
			fmt.Println("Created it!")

		} else if (req[0:1] == "q") {

	     	ani, ok := m[s1+s2]
	    	if !ok {
	        	fmt.Println("Not Created Yet !")
		    } else {
		        if s2 == "eat" {
		          	ani.Eat()
				} else if s2 == "move" {
					ani.Move()
				} else if s2 == "speak" {
					ani.Speak()
				} else {
					fmt.Println("You are not allowed to use such call")
				}
			}
		} 
	}
}