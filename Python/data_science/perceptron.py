import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Carregando os dados de treinamento para a porta lógica 'AND'
diabetes = pd.read_csv('.\Datasets\diabetes.csv')  # Substitua 'seu_arquivo.csv' pelo nome do seu arquivo CSV

X = diabetes.iloc[:, 1:9].values

Y = diabetes['class'].values
Y = pd.factorize(Y)[0]
Y = Y.astype(float)

# Treinamento do modelo durante 50 ciclos e taxa de 0.2
T = 1000
alpha = 0.001

# Função para treinamento do modelo durante T iterações
def perceptron(X, Y, T, alpha):
    N = X.shape[0]  # Número de exemplos de treinamento
    M = X.shape[1]

    # Transformar dados de treinamento para considerar o bias como um peso
    X = np.column_stack((X, np.ones(N)))

    # Inicialização aleatória dos pesos da rede com valores entre -1 e 1
    wt = np.random.uniform(-1, 1, size=(M+1,))

    list_sse = []

    # Ciclo de aprendizado
    for t in range(1, T+1):
        all_erros = []

        for j in range(N):
            # Exemplo atual
            xj = X[j]
            yj = Y[j]

            # Cálculo da soma acumulada dos pesos
            s = np.dot(wt, xj)

            # Cálculo da saída do perceptron com função de ativação sigmoid
            fj = 1 / (1 + np.exp(-s))

            # Cálculo do erro
            ej = yj - fj
            all_erros.append(ej)

            # Regra Delta
            wt = wt + alpha * ej * xj

        all_erros = np.array(all_erros)
        sse_t = np.sum(all_erros**2)
        print(f"Iteração: {t}. Erro: {sse_t:.2f}.")

        list_sse.append(sse_t)  # Soma dos erros quadrados na iteração t

    return {'modelo': wt, 'erros': list_sse, 'sse': list_sse[-1]}

modelo = perceptron(X, Y, T, alpha)
# Melhor Resultado: Iteração: 930. Erro: 271.81.

# Plot dos erros por iteração
plt.plot(range(1, T+1), modelo['erros'], 'b-')
plt.xlabel('Iteration')
plt.ylabel('Sum of Square Errors')
plt.show()