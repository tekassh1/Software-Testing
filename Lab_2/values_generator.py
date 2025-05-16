import math
import csv
import os

def sin(x):    return math.sin(x)
def cos(x):    return math.cos(x)
def tan(x):    return math.tan(x)
def cot(x):    return cos(x)/sin(x)
def sec(x):    return 1/cos(x)
def csc(x):    return 1/sin(x)

def ln(x):     return math.log(x)
def log2(x):   return math.log(x, 2)
def log3(x):   return math.log(x, 3)
def log5(x):   return math.log(x, 5)
def log10(x):  return math.log10(x)

def first_equation(x):
    return ((((((((((((sec(x) / sec(x)) - cot(x)) * sin(x)) * (cot(x) - (cos(x) - tan(x)))) ** 2) * (cos(x) ** 2)) ** 3) - tan(x)) ** 3) + (((cot(x) ** 2) + cot(x)) * ((csc(x) + (tan(x) - csc(x))) + csc(x)))) ** 2) - sin(x))

# x > 0
def second_equation(x):
    return (((((log2(x) ** 2) / (log5(x) - ln(x))) * (log5(x) / log10(x))) ** 2) - (ln(x) / log2(x)))


xs_neg = [ -10 + i*(10 - 0.001)/999 for i in range(1000) ]
xs_pos = [ 0.001 + i*(10 - 0.001)/999 for i in range(1000) ]

OUTPUT_DIR = "src/test/resources/system/values"
os.makedirs(OUTPUT_DIR, exist_ok=True)
DELIM = ';'

def write_csv(filename, header, rows):
    path = os.path.join(OUTPUT_DIR, filename)
    with open(path, 'w', newline='', encoding='utf-8') as f:
        writer = csv.writer(f, delimiter=DELIM)
        writer.writerow(header)
        writer.writerows(rows)


# First equation

# testFirstEquationAllMocked.csv
hdr = ["x","sin","cos","tan","cot","sec","csc","expected"]
rows = []
for x in xs_neg:
    rows.append([
        x,
        sin(x), cos(x), tan(x), cot(x),
        sec(x), csc(x),
        first_equation(x)
    ])
write_csv("testFirstEquationAllMocked.csv", hdr, rows)

# testFirstEquationWithCsc.csv
hdr = ["x","sin","cos","tan","cot","sec","expected"]
rows = [[x, sin(x), cos(x), tan(x), cot(x), sec(x), first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationWithCsc.csv", hdr, rows)

# testFirstEquationWithSec.csv
hdr = ["x","sin","cos","tan","cot","expected"]
rows = [[x, sin(x), cos(x), tan(x), cot(x), first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationWithSec.csv", hdr, rows)

# testFirstEquationWithCot.csv
hdr = ["x","sin","cos","tan","expected"]
rows = [[x, sin(x), cos(x), tan(x), first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationWithCot.csv", hdr, rows)

# testFirstEquationWithTan.csv
hdr = ["x","sin","cos", "expected"]
rows = [[x, sin(x), cos(x), first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationWithTan.csv", hdr, rows)

# testFirstEquationWithCos.csv
hdr = ["x","sin","expected"]
rows = [[x, sin(x), first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationWithCos.csv", hdr, rows)

# testFirstEquationNoMocked.csv
hdr = ["x","expected"]
rows = [[x, first_equation(x)] for x in xs_neg]
write_csv("testFirstEquationNoMocked.csv", hdr, rows)

# Second equation

# testSecondEquationAllMocked.csv
hdr = ["x","ln","log2","log3","log5","log10","expected"]
rows = [[x, ln(x), log2(x), log3(x), log5(x), log10(x), second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationAllMocked.csv", hdr, rows)

# testSecondEquationWithLog10.csv
hdr = ["x","ln","log2","log3","log5","expected"]
rows = [[x, ln(x), log2(x), log3(x), log5(x), second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationWithLog10.csv", hdr, rows)

# testSecondEquationWithLog5.csv
hdr = ["x","ln","log2","log3","expected"]
rows = [[x, ln(x), log2(x), log3(x), second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationWithLog5.csv", hdr, rows)

# testSecondEquationWithLog3.csv
hdr = ["x","ln","log2","expected"]
rows = [[x, ln(x), log2(x), second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationWithLog3.csv", hdr, rows)

# testSecondEquationWithLog2.csv
hdr = ["x","ln","expected"]
rows = [[x, ln(x), second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationWithLog2.csv", hdr, rows)

# testSecondEquationNoMocked.csv
hdr = ["x","expected"]
rows = [[x, second_equation(x)] for x in xs_pos]
write_csv("testSecondEquationNoMocked.csv", hdr, rows)
