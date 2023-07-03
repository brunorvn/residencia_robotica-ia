"""
train.py: 
    Module for training and evaluating a K-Nearest Neighbors (KNN) classifier on the Iris dataset.

This module performs the following steps:
    1. Imports necessary libraries: 
        numpy, sklearn.datasets, sklearn.model_selection, matplotlib.pyplot, and matplotlib.colors.
    2. Sets up the colormap for plotting.
    3. Loads the Iris dataset and assigns the features and target variable.
    4. Splits the data into training and testing sets.
    5. Creates an instance of the KNN classifier.
    6. Fits the classifier to the training data.
    7. Makes predictions on the testing data.
    8. Calculates the accuracy of the predictions.
    9. Prints the accuracy as a percentage.
    10. Creates a scatter plot of the Iris dataset.

Example usage:
    $ python train.py

Output:
    Accuracy: XX.XX%
    Scatter plot of the Loaded dataset.

"""
import numpy as np
from sklearn import datasets
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap
import algos.algos as al

cmap = ListedColormap(['#FF0000',"#00FF00","#0000FF"])

#pylint: disable=no-member
iris = datasets.load_iris()
X, y = iris.data, iris.target

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=1234)

clf = al.KNN(k=5)

clf.fit(X_train, y_train)
predictions = clf.predict(X_test)

acc = np.sum(predictions == y_test) / len(y_test) # Acurácia
print(f"Acc: {acc*100}%")

plt.figure()
plt.scatter(X[:,2], X[:,3], c=y, cmap=cmap, edgecolor='k', s=20)
plt.show()

acc = np.sum(predictions == y_test) / len(y_test) # Acurácia
print(f"Acurácia: {acc*100:.2f}%")
