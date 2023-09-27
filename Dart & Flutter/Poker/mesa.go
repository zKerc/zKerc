package main

import (
	"fmt"
)

func distribuirCartasMesa(mesa *Mesa, baralho []Carta, quantidade int) {
	mesa.CartasComunitarias = append(mesa.CartasComunitarias, baralho[:quantidade]...)
}

func printarInfoMesa(mesa *Mesa) {
	fmt.Print("CARTAS COMUNITÁRIAS\n")
	for _, carta := range mesa.CartasComunitarias {
		fmt.Printf("[%v%s]", carta.Valor, carta.Naipe)
	}
	fmt.Printf("\nPOTE: %d Fichas", mesa.Pote)
	fmt.Print("\n\n")
}
