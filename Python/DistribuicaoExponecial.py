import numpy as np
import matplotlib.pyplot as plt

# Parâmetro da média da distribuição exponencial
media = 1000

# Tempo mínimo desejado
tempo_minimo = 1200

# Função de densidade de probabilidade (PDF) da distribuição exponencial
def exponencial_pdf(x, media):
    return (1/media) * np.exp(-x/media)

# Função de sobrevivência da distribuição exponencial
def exponencial_sf(x, media):
    return np.exp(-x/media)

# Cálculo da probabilidade usando a função de sobrevivência da distribuição exponencial
probabilidade = exponencial_sf(tempo_minimo, media)

# Geração de valores para o eixo x do gráfico
x = np.linspace(0, 3000, 1000)

# Cálculo da função de densidade de probabilidade (PDF) para cada valor de x
y = exponencial_pdf(x, media)

# Plotagem do gráfico da distribuição exponencial
plt.plot(x, y, label='PDF da distribuição exponencial')
plt.fill_between(x, y, where=(x >= tempo_minimo), alpha=0.5, label='Probabilidade')
plt.xlabel('Tempo')
plt.ylabel('Densidade de probabilidade')
plt.title('Distribuição Exponencial')
plt.legend()
plt.show()

# Impressão da probabilidade calculada
print(f'A probabilidade de uma lâmpada durar pelo menos {tempo_minimo} horas é de {probabilidade:.4f}')
