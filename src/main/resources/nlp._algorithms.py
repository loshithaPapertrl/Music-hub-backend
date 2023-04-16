import mysql.connector as connection
import pandas as pd
from rake_nltk import Rake
import nltk

import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
import warnings



warnings.filterwarnings("ignore")

mydb = connection.connect(host="localhost:3307", database='music_hub', user="root", passwd="123", use_pure=True)
query = "Select * from profile_detail;"
result_dataFrame = pd.read_sql(query, mydb)
# print(result_dataFrame.head())
mydb.close()

#   ---- NLP

df = result_dataFrame  # Read dataset

# keep only these  useful columns,
df = df[['id', 'genres']]

# Step 2: data pre-processing
# to remove punctuations from Genres
df['genres'] = df['genres'].str.replace('[^\w\s]', '')

# # alternative way to remove punctuations, same result
import string

df['genres'] = df['genres'].str.replace('[{}]'.format(string.punctuation), '')

# to extract key words from Genres to a list
df['Key_words'] = ''  # initializing a new column
r = Rake()  # use Rake to discard stop words (based on english stopwords from NLTK)

for index, row in df.iterrows():
    r.extract_keywords_from_text(
        row['Genres'])  # to extract key words from Genres, default in lower case
    key_words_dict_scores = r.get_word_degrees()  # to get dictionary with key words and their scores
    row['Key_words'] = list(key_words_dict_scores.keys())  # to assign list of key words to new column

# to see last item in Genres
df['genres'][1]

# to see last dictionary extracted from Genres
key_words_dict_scores
# to see last item in Key_words
df['Key_words'][1]

# Step 3: create word representation by combining column attributes to Bag_of_words
# to combine 4 lists (4 columns) of key words into 1 sentence under Bag_of_words column
df['Bag_of_words'] = ''
columns = ['Key_words']

for index, row in df.iterrows():
    words = ''
    for col in columns:
        words += ' '.join(row[col]) + ' '
    row['Bag_of_words'] = words

# strip white spaces infront and behind, replace multiple whitespaces (if any)
df['Bag_of_words'] = df['Bag_of_words'].str.strip().str.replace('   ', ' ').str.replace('  ', ' ')

df = df[['Genres', 'Bag_of_words']]

# an example to see what is in the Bag_of_words
df['Bag_of_words'][0]

# Step 4: create vector representation for Bag_of_words and the similarity matrix
# to generate the count matrix
count = CountVectorizer()
count_matrix = count.fit_transform(df['Bag_of_words'])
count_matrix

cosine_sim = cosine_similarity(count_matrix, count_matrix)
# print(cosine_sim)

# Step 5: run and test the recommender model

# to create a Series for job Positions which can be used as indices (each index is mapped to a job Position)
indices = pd.Series(df['Genres'])


# print(indices)
# indices[:5]

# most simliar input

def recommend(Position, cosine_sim=cosine_sim):
    Recommend = []
    from fuzzywuzzy import process
    CollectedDataSet = result_dataFrame

    Genres = CollectedDataSet['genres'].tolist()
    str2Match = input_skill
    highest = process.extractOne(str2Match, Genres)[0]
    #  print(highest)
    idx = CollectedDataSet[CollectedDataSet['genres'] == highest].values[0][0]
    print(idx)


#   print(top_10_indices)
import sys

n = len(sys.argv)
for i in range(1):
    input_Key = (sys.argv[1])
    input_skill = input_Key
    recommend(input_skill)


#  ---- NLP
