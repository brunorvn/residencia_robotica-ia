from re import X
from collections import Counter
import numpy as np


def euclidean_distance(x_1, x_2):
    """
    Calculate the Euclidean distance between two vectors.

    Parameters:
        x_1 (array-like): The first vector.
        x_2 (array-like): The second vector.

    Returns:
        distance (float): The Euclidean distance between x_1 and x_2.

    Notes:
        - The input vectors x_1 and x_2 should have the same shape.
        - The function uses the NumPy library for the calculation.
        - The Euclidean distance is computed as the square root of the sum of squared differences
          between corresponding elements of x_1 and x_2.

    Example:
        x_1 = [1, 2, 3]
        x_2 = [4, 5, 6]
        distance = euclidean_distance(x_1, x_2)
        print(distance)
        # Output: 5.196152422706632
    """
    distance = np.sqrt(np.sum((x_1-x_2)**2))
    return distance


class KNN:
    """
    K-Nearest Neighbors (KNN) classifier.

    Parameters:
        k (int): The number of nearest neighbors to consider during classification. Default is 3.

    Methods:
        fit(X, y):
            Fit the KNN model with training data.

        predict(X):
            Predict the class labels for the given test data.

    Private Methods:
        _predict(x):
            Predict the class label for a single test instance.

    Attributes:
        X_train (array-like): The training data features.
        y_train (array-like): The training data labels.

    Notes:
        - The KNN algorithm assigns a class label to a test instance based on the majority class
          among its k nearest neighbors in the training data.
        - The Euclidean distance metric is used to measure the similarity between instances.
        - The fit method should be called before making predictions to set the training data.

    Example:
        knn = KNN(k=5)
        knn.fit(X_train, y_train)
        y_pred = knn.predict(X_test)
    """
    def __init__(self, k=3) -> None:
        self.k = k
        self.X_train
        self.y_train

    def fit(self, X, y):
        """
        Fit the KNN model with training data.

        Parameters:
            X (array-like): The training data features.
            y (array-like): The training data labels.

        Returns:
            None
        """
        self.X_train = X
        self.y_train = y

    def predict(self, X):
        """
        Predict the class labels for the given test data.

        Parameters:
            X (array-like): The test data features.

        Returns:
            predictions (list): The predicted class labels for each test instance.
        """
        predictions = [self._predict(x) for x in X]
        return predictions

    def _predict(self, x):
        """
        Predict the class label for a single test instance.

        Parameters:
            x (array-like): A single test instance.

        Returns:
            label: The predicted class label for the test instance.
        """
        # compute the eucl distance
        distances = [euclidean_distance(x, x_train)
                     for x_train in self.X_train]
        # get the closest k
        k_indices = np.argsort(distances)[:self.k]
        k_nearest_labels = [self.y_train[i] for i in k_indices]
        # majority vote
        most_common = Counter(k_nearest_labels).most_common()
        return most_common[0][0]


class LinearRegression:

    def __init__(self, learning_rate: float = 0.01, n_iters: int = 1000):
        self.learning_rate = learning_rate
        self.n_iters = n_iters
        self.weights = None
        self.bias = None
    
    def fit(self, X, y):
        n_samples, n_features = X.shape
        self.weights = np.zeros(n_features)
        self.bias = 0

        y_pred = np.dot(X, self.weights) + self.bias

        dw = (1/n_samples) * np.dot(X, (y_pred - y))

    def predict(self):
        pass