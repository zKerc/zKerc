package main

import (
	"fmt"
	"strings"
	"time"
)

func inicioRodadaApostas(jogadores []Jogador, mesa *Mesa) {
	apostaMinima := 20
	for i := 0; i <= 1; i++ {
		jogador := &jogadores[i]
		if i == 0 {
			fmt.Printf("Jogador: %s, SmallBlind %d\n", jogador.Nome, apostaMinima/2)
			jogador.Dinheiro -= apostaMinima / 2
			mesa.Pote += apostaMinima / 2
		} else {
			fmt.Printf("Jogador: %s, BigBlind %d\n", jogador.Nome, apostaMinima)
			jogador.Dinheiro -= apostaMinima
			mesa.Pote += apostaMinima
		}

		timer := time.NewTimer(2 * time.Second)
		<-timer.C
	}
}

func primeiraRodadaApostas(jogadores []Jogador, mesa *Mesa) {
	apostaMinima := 20
	for i := 2; i < len(jogadores); i++ {
		jogador := &jogadores[i]
		if jogador.Ativo {
			fmt.Printf("%s, deseja apostar (Sim/Não)? ", jogador.Nome)
			if solicitarRespostaSimOuNao() {
				aposta := fazerAposta(jogador, apostaMinima)
				mesa.Pote += aposta
				if aposta > apostaMinima {
					apostaMinima = aposta
				}
			} else {
				jogador.Ativo = false
				apostaMinima = 0
			}
		}
	}
}

func solicitarRespostaSimOuNao() bool {
	for {
		var resposta string
		fmt.Scanln(&resposta)
		resposta = strings.ToLower(resposta)
		if resposta == "sim" || resposta == "s" {
			return true
		} else if resposta == "não" || resposta == "nao" || resposta == "n" {
			return false
		} else {
			fmt.Print("Por favor, responda sim ou não: ")
		}
	}
}

func rodadaApostas(jogadores []Jogador, mesa *Mesa) {
	apostaMinima := 20
	for i := range jogadores {
		jogador := &jogadores[i]
		if jogador.Ativo {
			fmt.Printf("%s, deseja apostar (Sim/Não)? ", jogador.Nome)
			if solicitarRespostaSimOuNao() {
				aposta := fazerAposta(jogador, apostaMinima)
				mesa.Pote += aposta
				if aposta > apostaMinima {
					apostaMinima = aposta
				}
			} else {
				jogador.Ativo = false
				apostaMinima = 0
			}
		}
	}
}

func fazerAposta(jogador *Jogador, apostaMinima int) int {
	for {
		fmt.Printf("%s, quanto deseja apostar? (mínimo R$%d): ", jogador.Nome, apostaMinima)
		var aposta int
		_, err := fmt.Scan(&aposta)
		if err != nil || aposta < apostaMinima {
			fmt.Printf("Aposta inválida! Deve ser um número maior ou igual a %d. Tente novamente: ", apostaMinima)
			continue
		}
		jogador.Aposta = aposta
		jogador.Dinheiro -= aposta
		return aposta
	}
}

func jogarPoker(jogadores []Jogador, mesa *Mesa, baralho []Carta) {
	fmt.Print("Decidindo o dealer...\n")
	timer := time.NewTimer(2 * time.Second)
	<-timer.C
	dealer := DeterminarDealer(jogadores)
	fmt.Printf("O dealer sorteado foi: %s\n", dealer.Nome)

	RearranjarJogadores(jogadores, dealer)
	rodada := 1

	for {
		switch rodada {
		case 1:
			fmt.Println("Início da Rodada 1: Pré-flop")
			if verificarFimDeJogo(jogadores, mesa) {
				return // Se houver apenas um jogador ativo, encerre o jogo
			}
			inicioRodadaApostas(jogadores, mesa)
			distribuirCartas(jogadores, baralho, 2)
			printarInfos(jogadores)
			primeiraRodadaApostas(jogadores, mesa)
			distribuirCartasMesa(mesa, baralho, 3) // flop
		case 2:
			fmt.Println("Início da Rodada 2: Flop")
			if verificarFimDeJogo(jogadores, mesa) {
				return // Se houver apenas um jogador ativo, encerre o jogo
			}
			printarInfos(jogadores)
			printarInfoMesa(mesa)
			rodadaApostas(jogadores, mesa)
			distribuirCartasMesa(mesa, baralho, 1) // turn
		case 3:
			fmt.Println("Início da Rodada 3: Turn")
			if verificarFimDeJogo(jogadores, mesa) {
				return // Se houver apenas um jogador ativo, encerre o jogo
			}
			printarInfos(jogadores)
			printarInfoMesa(mesa)
			rodadaApostas(jogadores, mesa)
			distribuirCartasMesa(mesa, baralho, 1) // river
		case 4:
			fmt.Println("Início da Rodada 4: River")
			if verificarFimDeJogo(jogadores, mesa) {
				return // Se houver apenas um jogador ativo, encerre o jogo
			}
			printarInfos(jogadores)
			printarInfoMesa(mesa)
			rodadaApostas(jogadores, mesa)
			decidirVencedor(jogadores, mesa) // Aqui você vai implementar a lógica para decidir o vencedor baseado nas regras do poker.
		}
		rodada++
	}
}

func verificarFimDeJogo(jogadores []Jogador, mesa *Mesa) bool {
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
		return true   // Retorna true indicando que o jogo deve terminar
	}
	return false // Retorna false indicando que o jogo deve continuar
}

func decidirVencedor(jogadores []Jogador, mesa *Mesa) {
	var vencedores []Jogador
	var maiorAvaliacao AvaliacaoMao

	for i := range jogadores {
		jogador := &jogadores[i]
		if jogador.Ativo {
			cartas := append(jogador.Mao, mesa.CartasComunitarias...)
			avaliacao := avaliarMao(cartas)

			if len(vencedores) == 0 || avaliacao.Tipo > maiorAvaliacao.Tipo {
				vencedores = []Jogador{*jogador}
				maiorAvaliacao = avaliacao
			} else if avaliacao.Tipo == maiorAvaliacao.Tipo {
				// Empate com a mão atual, adiciona ao grupo de vencedores
				vencedores = append(vencedores, *jogador)
			}
		}
	}

	if len(vencedores) == 1 {
		vencedor := vencedores[0]
		fmt.Printf("O vencedor é %s com %v\n", vencedor.Nome, maiorAvaliacao.Tipo)
		premiarVencedor(&vencedor, mesa)
	} else if len(vencedores) > 1 {
		// Empate entre múltiplos jogadores, divida o pote
		fmt.Printf("Empate entre os seguintes jogadores:\n")
		for _, vencedor := range vencedores {
			fmt.Printf("%s com %v\n", vencedor.Nome, maiorAvaliacao.Tipo)
			premiarVencedor(&vencedor, mesa)
		}
	} else {
		fmt.Println("Não há vencedor.")
	}
}

func premiarVencedor(jogador *Jogador, mesa *Mesa) {
	jogador.Dinheiro += mesa.Pote
	mesa.Pote = 0 // Resetar o pote para a próxima rodada
}

func confirmarContinuacao() bool {
	fmt.Print("Deseja continuar jogando? (Sim/Não): ")
	return solicitarRespostaSimOuNao()
}
