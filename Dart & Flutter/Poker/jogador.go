package main

import (
	"fmt"
	"math/rand"
	"time"
)

func criarJogador(nome string, dinheiro int) Jogador {
	return Jogador{Nome: nome, Mao: []Carta{}, Dinheiro: dinheiro, Aposta: 0, Ativo: true}
}

func inicializarJogadores() []Jogador {
	return []Jogador{
		criarJogador("Ian", 1000),
		criarJogador("Fulano", 1000),
		criarJogador("CPU 0", 1000),
		criarJogador("Kaio", 1000),
		criarJogador("CPU 1", 1000),
	}
}

func printarInfos(jogadores []Jogador) {
	for _, jogador := range jogadores {
		if jogador.Ativo {
			fmt.Printf("Nome: %s | Fichas: %d\n", jogador.Nome, jogador.Dinheiro)
			fmt.Printf("Aposta: %d\n", jogador.Aposta)
			fmt.Print("Cartas: ")
			for _, carta := range jogador.Mao {
				fmt.Printf("[%v%s]", carta.Valor, carta.Naipe)
			}
			fmt.Print("\n")
			fmt.Println()
		}
	}
}
func RearranjarJogadores(jogadores []Jogador, dealer Jogador) {
	// Encontre a posição do dealer
	dealerIndex := -1
	for i, jogador := range jogadores {
		if jogador.Nome == dealer.Nome {
			dealerIndex = i
			break
		}
	}

	if dealerIndex == -1 {
		// Dealer não encontrado na lista de jogadores
		return
	}

	// Rearranje a lista de jogadores com base na ordem da mesa
	numJogadores := len(jogadores)
	novaOrdem := make([]Jogador, numJogadores)
	copy(novaOrdem[0:], jogadores[dealerIndex+1:])
	copy(novaOrdem[numJogadores-dealerIndex-1:], jogadores[:dealerIndex+1])
	copy(jogadores, novaOrdem)
}

func DeterminarDealer(jogadores []Jogador) Jogador {
	// Inicialize a semente do gerador de números aleatórios
	rand.Seed(time.Now().UnixNano())

	// Atribua números aleatórios aos jogadores
	for i := range jogadores {
		jogadores[i].NumSorte = rand.Intn(13) + 2 // Números de 2 a 14
	}

	// Encontre o jogador com o número mais alto como dealer
	var dealer Jogador
	for _, jogador := range jogadores {
		if jogador.NumSorte > dealer.NumSorte {
			dealer = jogador
		}
	}
	return dealer
}

func verificarJogadoresAtivos(jogadores []Jogador, mesa *Mesa) bool {
	jogadoresAtivos := 0
	var ultimoJogadorAtivo *Jogador

	// Conta quantos jogadores estão ativos e guarda o último jogador ativo encontrado.
	for i := range jogadores {
		jogador := &jogadores[i]
		if jogador.Ativo {
			jogadoresAtivos++
			ultimoJogadorAtivo = jogador
		}
	}

	if jogadoresAtivos == 1 {
		fmt.Printf("O vencedor é %s, pois todos os outros jogadores desistiram!\n", ultimoJogadorAtivo.Nome)
		ultimoJogadorAtivo.Dinheiro += mesa.Pote
		mesa.Pote = 0 // Resetar o pote para a próxima rodada
		return false  // Retorna falso indicando que o jogo deve terminar
	}
	return true // Retorna verdadeiro indicando que o jogo deve continuar
}
