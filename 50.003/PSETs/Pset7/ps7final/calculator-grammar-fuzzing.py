import random

# Expr:= Expr+ Term | Expr–Term | Term
# Term := Term * Factor | Term / Factor | Factor
# Factor := -Integer | (Expr) | Integer | Integer.Integer
# Integer := Digit | IntegerDigit
# Digit := 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9


DEPTH = 3

def grammar_fuzzing():
    return grammar_expr(DEPTH)             # calling for first time
    
def grammar_expr(depth):
    rules_expr = ["+","-", ""]
    string = ""
    randomi = random.randint(0,2)
#     print("operator is ", rules_expr[randomi])
    if randomi != 2 and depth > 1:
        string += grammar_expr(depth-1)
        string += rules_expr[randomi]
        string += grammar_term(DEPTH)            #calling for first time
    else:
        string += grammar_term(DEPTH)
    return string

def grammar_term(depth):
    rules_term = ["*", "/", ""]
    string = ""
    randomi = random.randint(0,2)
    
    if randomi != 2 and depth > 1:
        string += grammar_term(depth-1)
        string += rules_term[randomi]
        string += grammar_factor(depth-1)
    else:   # only factor
        string += grammar_factor(depth-1)
    return string       
    

def grammar_factor(depth):
#     although not stated in the grammar rules, 
#     If a factor is -Int, it should be wrapped in brackets
#     EG. (-4)
    rules_factor = ["-", "()", "", "."]
    string = ""
    randomi = random.randint(0,3)
    if randomi == 0:
        string += "(-"
        string += grammar_integer(DEPTH)
        string += ")"
    elif randomi == 2:   # -Int or Int
        string += rules_factor[randomi]
        string += grammar_integer(DEPTH)
    elif randomi == 1: #()
        string += "("
        string += grammar_expr(depth)
        string += ")"
    else:   # Int.Int
        string += grammar_integer(DEPTH)
        string += "."
        string += grammar_integer(DEPTH)
    return string
    
    
def grammar_integer(depth):
    string = ""
    randomi = random.randint(0,1)
    if randomi == 0:   #just digit
        return grammar_digit(False)
    else:
        string += grammar_digit(True)
        string += grammar_integer(depth-1)
        return string
    

def grammar_digit(boolean):
#     If grammar_digit is used for grammar_integer, the first digit should not be zero
#     When boolean=True, grammar_digit will not return zero    
    if boolean:
        return str(random.randint(1,9))
    randomi = random.randint(0,9)
    return str(randomi)
    

print(grammar_fuzzing())


