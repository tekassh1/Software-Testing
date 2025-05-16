import pandas as pd
import plotly.express as px

df = pd.read_csv("points.csv", sep=',')

fig = px.line(df, x="x", y="y", title="График функции")
fig.update_traces(mode="lines+markers")


fig.show()
