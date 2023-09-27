package main

import (
	"fmt"
)

// Naipe representa os naipes das cartas.
type Naipe string

const (
	Paus    Naipe = "\u2663"
	Ouros   Naipe = "\u2666"
	Copas   Naipe = "\u2665"
	Espadas Naipe = "\u2660"
)

// Carta representa uma carta do baralho.
type Carta struct {
	Valor string
	Naipe Naipe
}

type Jogador struct {
	Nome     string
	Mao      []Carta
	Cartas   []Carta
	Dinheiro int
	Aposta   int
	Ativo    bool
	NumSorte int
}

type Mesa struct {
	CartasComunitarias []Carta
	Pote               int
}

type Mao int

const (
	AltaCarta Mao = iota
	UmPar
	DoisPares
	Trinca
	Sequencia
	Flush
	FullHouse
	Quadra
	SequenciaFlush
	RoyalFlush
)

type AvaliacaoMao struct {
	Tipo   Mao
	Cartas []Carta
}

func main() {

	for {
		jogadores := inicializarJogadores()
		fmt.Print("Deseja jogar uma partida de poker? (Sim/Não): ")
		if !solicitarRespostaSimOuNao() {
			fmt.Println("Até logo!")
			break
		}
		baralho := criarEEmbaralharBaralho()
		mesa := &Mesa{}
		jogarPoker(jogadores, mesa, baralho)
	}
}
