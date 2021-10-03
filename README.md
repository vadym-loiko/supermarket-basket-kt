Supermarket basket component

List of products is managed internally

Predefined list of products:

Item   Unit Price  Special Price
--------------------------------
    A     40         3 for 70
    B     10         2 for 15
    C     30
    D     25

to get list of all available products use : GET /product/all

User is able to check list of all available baskets, open new basket, add/remove items from basket,
get total price with personal/total discounts.

Manage basket API:

get list of all baskets : GET /basket/all

open new basket: POST /basket/new

add new item into basket: POST /basket/{basketId}/addItem

get basket info with list of all items in basket: GET /basket/{basketId}

remove item from basket: DELETE /basket/{basketId}/{basketItemId}

calculate total price with personal discount: GET /basket/{basketId}/totalPrice/personalDiscount

calculate total price with total discount: GET /basket/{basketId}/totalPrice

Types of discount:
- personal discount : promotion will be calculated for all items separately
- total discount    : promotion will be calculated for aggregated amount for each product
