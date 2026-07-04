import tkinter
#Setting out the values fore the column and row, and the column and row count for the buttons
button_values = [
    ["AC", "+/-", "%", "/"],
    ["7", "8", "9", "*"],
    ["4", "5", "6", "-"],
    ["1", "2", "3", "+"],
    ["0", ".", "√", "="]
]

right_symbols = ["/", "*", "-", "+", "="]
top_symbols = ["AC", "+/-", "%"]

row_count = len(button_values)
column_count = len(button_values[0])

#Colors to be used for the foreground and background
light_gray = "#D4D4D2"
black = "#1c1c1c"
dark_gray = "#505050"
orange = "#ff9500"
white = "#ffffff"

window = tkinter.Tk()
window.title("Calculator")
window.resizable(False, False)

frame = tkinter.Frame(window)
label = tkinter.Label(frame, text = "0", 
                      font = ("Arial", 45), 
                      background = black, 
                      foreground = white,
                      anchor = "e",
                      width = column_count)
#width = column_count stops the calculator window from expanding as more numbers populate the claculation area that the label enables to be displayed. Since the buttons do not scale the expansion could lead to graphical issues.
#label.pack()
label.grid(row=0, column=0, 
           columnspan=column_count, 
           sticky="we")
for row in range(row_count):
    for column in range(column_count):
        value = button_values[row][column]
        button = tkinter.Button(frame, text=value, 
                                font=("Arial", 30),
                                width=column_count-1, 
                                height=1,
                                command = lambda value=value: button_click(value))
        if value in top_symbols:
            button.config(foreground=black,
                          background=light_gray)
        elif value in right_symbols:
            button.config(foreground=white,
                          background=orange)
        else:
            button.config(foreground=white, 
                          background=dark_gray)
        button.grid(row=row+1, column=column)
frame.pack()

#This is to set the values of A, B and the operator to be used in the calculation. The operator is set to None as it will be set when the user clicks on one of the operator buttons. A and B are set to 0 and None respectively as they will be set when the user clicks on a number button.
#A+B, A-B, A*B, A/B
A=0
operator= None
B=None

def clear_all():
    global A, B, operator
    A=0
    operator= None
    B=None
    #label["text"] = "0"

def check_zero_decimal(num):
    #This function checks if the number is a decimal and if it is, it checks if the number is 0. If it is, it returns True, otherwise it returns False. If the number is not a decimal, it returns False. Replaces the str(result) in if vallue == "+/-" label segment.
    if num % 1 == 0:
        num = int(num)
    num_str = str(num)
    if len(num_str) > 8:
        num_str = num_str[:8]
    #Discovered resolving the graphical glitch of the label expanfing now meant the numbers would expand beyond the label area. So like on a real calculator I had to limit it, placing it here to test if this is the best place to put it. Had it in the area where I determined the "0123456789" buttons but it was not universal and square root of 5 still broke the parameter of 8 characters. Hopefully here makes it universal for all of the code.
    #I  had to make a new variable to then index the new variable to the first 8 characters to ensure the numbers didn't bypass the labels length.
    return num_str
    
    #This function is used to determine what happens when a button is clicked. It takes in the value of the button that was clicked and performs the appropriate action based on that value.


def button_click(value):
    global right_symbols, top_symbols, A, B, operator
    if value in right_symbols:
        if value == "=":
            if A is not None and operator is not None:
                B = label["text"]
                numA = float(A)
                numB = float(B)
                if operator == "+":
                    label["text"] = check_zero_decimal(numA + numB)
                elif operator == "-":
                    label["text"] = check_zero_decimal(numA - numB)
                elif operator == "*":
                    label["text"] = check_zero_decimal(numA * numB)
                elif operator == "/":
                    label["text"] = check_zero_decimal(numA / numB)
            clear_all()

        elif value in "+-*/": 
            if operator is None:
                A = label["text"]
                label["text"] = "0"
                B = "0"
                #This ensures after preesing anumber and then a operaton the pressing of another operation like division then addition means you changed the operation sought to be done. 
            operator = value
    elif value in top_symbols:
        if value == "AC":
            clear_all()
            label["text"] = "0"
            #At first I put this in the clear_all() function but then I realised that it would be better to have it here as it is more specific to the AC button. The clear_all() function is used to reset the values of A, B and operator to their default values. The label["text"] = "0" is used to reset the label to 0. Meaning it would reset the labels after pressing the operation desired where I wanted to perform the operator it cleared the entry.
        if value == "+/-":
            #Take the text label convert it to a float, multiply by -1, convert it back to a string and set the label text to that value.
            result = float(label["text"])*-1
            label["text"] = check_zero_decimal(result)
        if value == "%":
            result = float(label["text"])/100
            label["text"] = check_zero_decimal(result)
    else: #This is for the numpad and decimal place button, square root button
        if value == ".":
            if value not in label["text"]:
                label["text"] += value
        elif value == "√":
            result = float(label["text"])**0.5
            label["text"] = check_zero_decimal(result)
        elif value in "0123456789":
            if label["text"] == "0":
                label["text"] = value #This replaces the 0 with the value of the button
            else:
                label["text"] += value
        
"""
This segment ensures that the user cannot enter 5.....4 as only one decimal point should be allowed, this is not data entry for IP addresses or other areas where more than one decimal is needed. The code checks if the value is a decimal point and if it is not already in the label text, it adds it to the label text. This prevents multiple decimal points from being entered.
if value == ".":
            if value not in label["text"]:
                label["text"] += value
                
"""

#Used to center the window by determining the width and height of the screen and the window
window.update()
window_width = window.winfo_width()
window_height = window.winfo_height()
screen_width = window.winfo_screenwidth()
screen_height = window.winfo_screenheight()

window_x = int((screen_width/2) - (window_width/2))
window_y = int((screen_height/2) - (window_height/2))

#format = (w)x(h)+(x)+(y)
window.geometry(f"{window_width}x{window_height}+{window_x}+{window_y}")

window.mainloop()

#Build a calculator in python
#https://www.youtube.com/watch?v=28tj-IBfGH4