import math
import matplotlib.pyplot as plt

'''
Suponha que você está estudando o desempenho dos alunos em uma prova de matemática,
onde cada questão é do tipo múltipla escolha, com quatro opções de resposta.
A probabilidade de um aluno responder corretamente a cada questão é de 0,3.
Obs.: A prova foi surpresa, portanto nenhum aluno estudou para a prova.

Qual é a probabilidade de um aluno acertar exatamente duas questões em um teste de cinco questões?
'''

# Função para calcular o coeficiente binomial, C n,k = (n / k) = n! / k! * (n-k)!
def coeficiente_binomial(n, k):
    return math.factorial(n) / (math.factorial(k) * math.factorial(n - k))

# Função para calcular a probabilidade da distribuição binomial (P(X = k) = (n / k) * p^k * q^n-k)
def probabilidade_binomial(n, k, p):
    return coeficiente_binomial(n, k) * (p ** k) * ((1 - p) ** (n - k))

# Parâmetros da questão
n = 5  # número de questões
p = 0.3  # probabilidade de acerto em uma questão

# Cálculo das probabilidades para cada quantidade de acertos possível
probabilidades = []
for k in range(n + 1):
    probabilidade = probabilidade_binomial(n, k, p)
    probabilidades.append(probabilidade)
    print(f"Probabilidade de acertar {k} questões: {probabilidade:.4f}")

    
# Criação do histograma
plt.bar(range(n + 1), probabilidades)
plt.xlabel('Número de acertos = k')
plt.ylabel('Probabilidade')
plt.title(f'Distribuição Binomial: {n} questões, p = {p}')
plt.show()
