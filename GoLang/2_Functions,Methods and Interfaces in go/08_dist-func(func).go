package main

import (
	"fmt"
    "strconv"
	)

func GenDisplaceFn(AccVelDist ...float64) func (float64) float64{

	avd := AccVelDist
	dist := func (t float64) float64{
		return ((avd[0]*t*t)/2 + avd[1]*t +avd[2])
	}
	return dist 
}

func main(){
	var n float64
	avd := make([]float64 ,3)
	fmt.Print("Acceleration ==> ")
	fmt.Scanln(&n)
	avd[0]=n
	fmt.Print("initial velocity ==> ")
	fmt.Scanln(&n)
	avd[1]=n
	fmt.Print("initial dist ==> ")
	fmt.Scanln(&n)
	avd[2]=n
	//avd := []float64 {5,3,2}
	fn := GenDisplaceFn(avd...)
	var s string
	for i :=0 ;i<10 ;i++{
    	fmt.Print("time (press x to break) ==>")
    	fmt.Scanln(&s)
    	if s=="x" || s=="X"{
    	    break
    	}
    	x ,_ := strconv.ParseFloat(s, 64)
    	fmt.Println(fn(x))
	}
}