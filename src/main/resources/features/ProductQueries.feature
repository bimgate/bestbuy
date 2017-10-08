Feature: Create get request and consume proper response from API

  Background:
    Given server API is up and running on "http://localhost:3030/products/" with response code 200

  Scenario: Get product with ID of 9132294
    When client send get request "http://localhost:3030/products/9132294"
    Then server API should response with JSON payload:
    """
    {
	"id": 9132294,
	"name": "Yamaha - P32D Pianica - Brown/White",
	"type": "HardGood",
	"price": 59.99,
	"upc": "086792895093",
	"shipping": 0,
	"description": "Keyboard wind instrument; designed for general music education; mouthpiece, anticorrosive reed and blowing pipe; 32-note design; shock-resistant, double-walled, blow-molded case",
	"manufacturer": "Yamaha",
	"model": "EN033P32D",
	"url": "http://www.bestbuy.com/site/yamaha-p32d-pianica-brown-white/9132294.p?id=1218990144149&skuId=9132294&cmp=RMXCC",
	"image": "http://img.bbystatic.com/BestBuy_US/images/products/9132/9132294_sa.jpg",
	"createdAt": "2016-11-17T17:58:46.655Z",
	"updatedAt": "2016-11-17T17:58:46.655Z",
	"categories": [{
			"id": "abcat0207000",
			"name": "Musical Instruments",
			"createdAt": "2016-11-17T17:57:04.285Z",
			"updatedAt": "2016-11-17T17:57:04.285Z"
		}, {
			"id": "pcmcat151600050037",
			"name": "Keyboards",
			"createdAt": "2016-11-17T17:57:04.899Z",
			"updatedAt": "2016-11-17T17:57:04.899Z"
		}, {
			"id": "pcmcat151600050040",
			"name": "Portable Keyboards",
			"createdAt": "2016-11-17T17:57:04.899Z",
			"updatedAt": "2016-11-17T17:57:04.899Z"
		}
	]
}
    """

  Scenario Outline: Get all products
    When client send get request "http://localhost:3030/products"
    Then server API should response with JSON which contains list of all products
    And every product should has id "<id>", model "<model>":
    Examples: Product elements
      | id     | model     |
      | 48530  | MN1500B4Z |
      | 127687 | MN1500B8Z |
      | 150115 | E91BP-4   |
      | 185230 | MN1400R4Z |
      | 185267 | MN1300R4Z |
      | 309062 | TS-X200   |
      | 312290 | MN1604B2Z |
      | 333179 | E90BP-2   |
      | 346575 | 99-5512   |

  Scenario: Get all products, limit to 1 result
    When client send get request "http://localhost:3030/products?$limit=1"
    Then server API should response with 1 product JSON
    And with "limit" field value "1"

  Scenario: Get all products, skip to the 25,001th result
    When client send get request "http://localhost:3030/products?$skip=25000"
    Then server API response JSON should has "skip" value "25000"

  Scenario: Get all products, sort by highest price (descending)
    When client send get request "http://localhost:3030/products?$sort[price]=-1"
    Then server API response JSON should has products "price" ordered from highest to lowest

  Scenario: Get all products, sort by lowest price (ascending)
    When client send get request "http://localhost:3030/products?$sort[price]=1"
    Then server API response JSON should has products "price" ordered from lowest to highest

  Scenario: Get all products, but only show the name and price in the result
    When client send get request "http://localhost:3030/products?$select[]=name&$select[]=price"
    Then server API response JSON should contain only "name" and "price" for all products

  Scenario: Get products of type HardGood
    When client send get request "http://localhost:3030/products?type=HardGood"
    Then server API response JSON should contain only products which "type" is "HardGood"

  Scenario: Get products less than or equal to $1.00
    When client send get request "http://localhost:3030/products?price[$lte]=1"
    Then server API response JSON should contain only products with "price" lest than or equal "1.00"

  Scenario: Get products that have 'star wars' in the name and are under $30
    When client send get request "http://localhost:3030/products?name[$like]=*star+wars*&price[$lt]=30"
    Then server API response JSON should contain only products with text "star wars" in "name" and "price" less than "30"

  Scenario: Get products that are either $0.99 or $1.99
    When client send get request "http://localhost:3030/products?price[$in]=0.99&price[$in]=1.99"
    Then server API response JSON should contain only products with "price" "0.99" or "1.99"

  Scenario: Get products that have a shipping price of more than $10
    When client send get request "http://localhost:3030/products?shipping[$gt]=10"
    Then server API response JSON should contain only products with "shipping" more than "10"

  Scenario: Get products that are not HardGood or Software
    When client send get request "http://localhost:3030/products?type[$nin][]=HardGood&type[$nin][]=Software"
    Then server API response JSON should contain only products which "type" is not "HardGood" or "Software"

  Scenario: Get products that are in category name "Coffee Pods"
    When client send get request "http://localhost:3030/products?category.name=Coffee%20Pods"
    Then server API response JSON should contain only products which are in "categories", "name" "Coffee Pods"

  Scenario: Get products that are in category ID "abcat0106004" (TV Mounts)
    When client send get request "http://localhost:3030/products?category.id=abcat0106004"
    Then server API response JSON should contain only products which are in "categories", "id" "abcat0106004"


