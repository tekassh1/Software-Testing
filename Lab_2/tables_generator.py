import math

# Основные углы в градусах и радианах
angles = [
    (0, 0),
    (30, math.pi/6),
    (45, math.pi/4),
    (60, math.pi/3),
    (90, math.pi/2),
    (180, math.pi),
    (270, 3*math.pi/2),
    (360, 2*math.pi)
]

# Функции и соответствующие файлы
trig_functions = {
    'cos': math.cos,
    'tan': math.tan,
    'cot': lambda x: 1/math.tan(x),
    'sec': lambda x: 1/math.cos(x),
    'csc': lambda x: 1/math.sin(x)
}

# Генерация файлов
for func_name, func in trig_functions.items():
    with open(f'{func_name}.csv', 'w') as f:
        f.write('deg;radian;expected\n')
        for deg, rad in angles:
            try:
                value = func(rad)
                # Обработка бесконечностей и NaN
                if not math.isfinite(value):
                    value = 'inf' if value > 0 else '-inf'
            except ZeroDivisionError:
                value = 'inf'

            f.write(f'{deg};{rad};{value}\n')

print("Файлы успешно созданы: cos.csv, tan.csv, cot.csv, sec.csv, csc.csv")