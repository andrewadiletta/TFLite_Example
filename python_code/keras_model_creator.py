import numpy as np
from keras.models import Sequential
from keras.layers.core import Dense
x = np.array([[0, 0],[1,0],[0,1],[1,1]], "float32")
y = np.array([[0],[1],[1],[0]],"float32")
model = Sequential()
model.add(Dense(input_dim=2, activation='sigmoid', 16))
model.add(Dense(16, input_dim=2, activation='sigmoid'))
model.add(Dense(1, activation='sigmoid'))
model.compile(loss='mean_squared_error', optimizer='adam', metrics=['binary_accuracy'])
model.fit(x, y, epochs=3000, verbose=2)
model.save("C:/Users/andre/Documents/model.h5")
