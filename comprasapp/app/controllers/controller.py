from app import app
from app.models import items, ammount

@app.route('/add', methods=['POST'])
def add():
    item_name = request.form['item']
    item_price = request.form['price']
    item_quantity = request.form['quantity']
    items[item_name] = [item_price, item_quantity]
    sum_i(convert_to_float(item_price), item_quantity)
    return redirect('/')


@app.route("/")
def main():
    return render_template('index.html', 
                           title= "Compras",
                           items=items)

@app.route('/add', methods=['POST'])
def add():
    item_name = request.form['item']
    item_price = request.form['price']
    item_quantity = request.form['quantity']
    items[item_name] = [item_price, item_quantity]
    sum_i(convert_to_float(item_price), item_quantity)
    return redirect('/')

def sum_i(price, quantity):
    global ammount
    ammount += float(price) * float(quantity)

def convert_to_float(input_string):
    # Replace commas with dots
    modified_string = input_string.replace(',', '.')

    # Convert to float
    result = float(modified_string)
    
    return result