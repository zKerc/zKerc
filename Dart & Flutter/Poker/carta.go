package main

import (
	"math/rand"
	"sort"
	"time"
)

var valores = map[string]int{
	"2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10,
	"J": 11, "Q": 12, "K": 13, "A": 14,
}

func valorCarta(carta Carta) int {
	return valores[carta.Valor]
}

func criarBaralho() []Carta {
	var baralho []Carta
	for _, valor := range append([]string{"2", "3", "4", "5", "6", "7", "8", "9", "10"}, "A", "J", "Q", "K") {
		for _, naipe := range []Naipe{Paus, Ouros, Copas, Espadas} {
			carta := Carta{Valor: valor, Naipe: naipe}
			baralho = append(baralho, carta)
		}
	}
	return baralho
}

func embaralharBaralho(baralho []Carta) {
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	for i := range baralho {
		j := r.Intn(len(baralho))
		baralho[i], baralho[j] = baralho[j], baralho[i]
	}
}

func criarEEmbaralharBaralho() []Carta {
	baralho := criarBaralho()
	embaralharBaralho(baralho)
	return baralho
}

func distribuirCartas(jogadores []Jogador, baralho []Carta, quantidade int) {
	for i := range jogadores {
		jogador := &jogadores[i]
		if jogador.Ativo {
			jogador.Mao = baralho[:quantidade]
			baralho = baralho[quantidade:]
		}
	}
}

func avaliarMao(cartas []Carta) AvaliacaoMao {
	// Ordene as cartas por valor
	sort.SliceStable(cartas, func(i, j int) bool {
		return valorCarta(cartas[i]) > valorCarta(cartas[j])
	})

	// Verifique se há um Royal Flush
	if temRoyalFlush(cartas) {
		return AvaliacaoMao{Tipo: RoyalFlush, Cartas: cartas[:5]}
	}

	// Verifique se há um Straight Flush
	if temStraightFlush(cartas) {
		return AvaliacaoMao{Tipo: SequenciaFlush, Cartas: cartas[:5]}
	}

	// Verifique se há uma Quadra
	if temQuadra(cartas) {
		return AvaliacaoMao{Tipo: Quadra, Cartas: cartas[:4]}
	}

	// Verifique se há um Full House
	if temFullHouse(cartas) {
		return AvaliacaoMao{Tipo: FullHouse, Cartas: cartas[:5]}
	}

	// Verifique se há um Flush
	if temFlush(cartas) {
		return AvaliacaoMao{Tipo: Flush, Cartas: cartas[:5]}
	}

	// Verifique se há uma Sequência (Straight)
	if temSequencia(cartas) {
		return AvaliacaoMao{Tipo: Sequencia, Cartas: cartas[:5]}
	}

	// Verifique se há uma Trinca
	if temTrinca(cartas) {
		return AvaliacaoMao{Tipo: Trinca, Cartas: cartas[:3]}
	}

	// Verifique se há Dois Pares
	if temDoisPares(cartas) {
		return AvaliacaoMao{Tipo: DoisPares, Cartas: cartas[:4]}
	}

	// Verifique se há um Par
	if temPar(cartas) {
		return AvaliacaoMao{Tipo: UmPar, Cartas: cartas[:2]}
	}

	// Se não corresponder a nenhum dos tipos acima, a mão é uma Alta Carta
	return AvaliacaoMao{Tipo: AltaCarta, Cartas: cartas[:1]}
}

// Funções auxiliares para verificar os diferentes tipos de mãos

func temRoyalFlush(cartas []Carta) bool {
	return temStraightFlush(cartas) && valorCarta(cartas[0]) == 14 // Ás é a carta mais alta
}

func temStraightFlush(cartas []Carta) bool {
	return temFlush(cartas) && temSequencia(cartas)
}

func temQuadra(cartas []Carta) bool {
	for i := 0; i < len(cartas)-3; i++ {
		if valorCarta(cartas[i]) == valorCarta(cartas[i+3]) {
			return true
		}
	}
	return false
}

func temFullHouse(cartas []Carta) bool {
	return temTrinca(cartas) && temDoisPares(cartas)
}

func temFlush(cartas []Carta) bool {
	naipe := cartas[0].Naipe
	for _, carta := range cartas {
		if carta.Naipe != naipe {
			return false
		}
	}
	return true
}

func temSequencia(cartas []Carta) bool {
	for i := 0; i < len(cartas)-1; i++ {
		if valorCarta(cartas[i])-1 != valorCarta(cartas[i+1]) {
			return false
		}
	}
	return true
}

func temTrinca(cartas []Carta) bool {
	for i := 0; i < len(cartas)-2; i++ {
		if valorCarta(cartas[i]) == valorCarta(cartas[i+2]) {
			return true
		}
	}
	return false
}

func temDoisPares(cartas []Carta) bool {
	pares := 0
	for i := 0; i < len(cartas)-1; i++ {
		if valorCarta(cartas[i]) == valorCarta(cartas[i+1]) {
			pares++
			i++ // Pule a próxima carta para evitar contar pares duplicados
		}
	}
	return pares == 2
}

func temPar(cartas []Carta) bool {
	for i := 0; i < len(cartas)-1; i++ {
		if valorCarta(cartas[i]) == valorCarta(cartas[i+1]) {
			return true
		}
	}
	return false
}
