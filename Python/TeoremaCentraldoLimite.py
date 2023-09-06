import random
import matplotlib.pyplot as plt

# Definir o número de elementos na lista
n = int(input("Digite o total de elementos da lista: \n"))

# Gera os elementos da lista
total_elementos = [random.randint(00, 99) for _ in range(n)]

# Define o número de amostras e o tamanho de cada amostra
num_amostras = int(input("Digite quantas amostras você quer: \n"))
tamanho_amostra = int(input("Digite o tamanho de cada amostra: \n"))

pe = sum(total_elementos) / len(total_elementos)

# Função para calcular a média de uma lista de números
def calcular_media(lista):
    return sum(lista) / len(lista)

# Realiza as amostras e calcula as probabilidades esperadas
pe_amostrais = []

for _ in range(num_amostras):
    amostra = random.sample(total_elementos, tamanho_amostra)
    pe_am = calcular_media(amostra)
    pe_amostrais.append(pe_am)

# Exibe os elementos, as amostras e as probabilidades esperadas amostrais e geral
print("Elementos:", total_elementos)
print("Probabilidade esperada:", pe)
print("Probabilidade esperada amostrais:", pe_amostrais)
print("Probabilidade geral:", sum(pe_amostrais) / num_amostras)

# Cria o gráfico de colunas da probabilidade geral
plt.bar(range(1, num_amostras + 1), pe_amostrais)
plt.axhline(y=sum(pe_amostrais) / num_amostras, color='r', linestyle='-')
plt.xlabel('Amostras')
plt.ylabel('Probabilidade Esperada')
plt.title('Gráfico de colunas da probabilidade geral')
plt.show()