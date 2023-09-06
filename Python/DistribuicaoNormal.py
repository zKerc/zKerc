import numpy as np
import matplotlib.pyplot as plt
import math

# Função para calcular a densidade de probabilidade da distribuição normal
def calcular_densidade_probabilidade(x, media, desvio_padrao):
    exponente = -((x - media) ** 2) / (2 * desvio_padrao ** 2)
    denominador = desvio_padrao * math.sqrt(2 * math.pi)
    return (1 / denominador) * math.exp(exponente)

# Parâmetros da distribuição normal
media = 170
desvio_padrao = 5

# a) Probabilidade de um adulto ter altura maior que 180 cm
limite_superior = 180
probabilidade_maior_180 = 1 - calcular_densidade_probabilidade(limite_superior, media, desvio_padrao)

# b) Probabilidade de altura estar entre 160 cm e 180 cm, dado que é maior que 160 cm
limite_inferior = 160
probabilidade_entre_160_180 = (calcular_densidade_probabilidade(limite_inferior, media, desvio_padrao) - calcular_densidade_probabilidade(limite_superior, media, desvio_padrao)) * -1

# Imprimir resultados
print("a) Probabilidade de altura maior que 180 cm:", probabilidade_maior_180)
print("b) Probabilidade de altura entre 160 cm e 180 cm (dado que é maior que 160 cm):", probabilidade_entre_160_180)

# Gerar gráfico da distribuição normal
x = np.linspace(media - 3 * desvio_padrao, media + 3 * desvio_padrao, 100)
y = [calcular_densidade_probabilidade(valor, media, desvio_padrao) for valor in x]

plt.plot(x, y)
plt.fill_between(x, y, where=(x >= limite_inferior) & (x <= limite_superior), color='gray', alpha=0.5)
plt.xlabel('Altura (cm)')
plt.ylabel('Densidade de probabilidade')
plt.title('Distribuição Normal: Alturas de Adultos')
plt.grid(True)
plt.show()
