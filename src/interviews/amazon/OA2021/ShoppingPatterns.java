package interviews.amazon.OA2021;

/**
 * Shoe Monster, a popular shopping website for atheletic shoes, would like to know which shoes are frequently bought together.
 *
 * When a customer purchases two shoes together, we log an edge between the two products. Three shoes that are interconnected form a triple.
 *
 * A score for a triple can be calculated by counting the total number of shoes outside the triple that are joined with a shoe inside the triple. This is the product sum.
 *
 * Find the minimum product sum in the graph, or return -1 if no triples exist.
 *
 * Input
 * The input to the function/method consists of four arguments:
 *
 * int products_nodes: the total number of shoe products for sale. Each shoe is identified by a number from 1 to product_nodes.
 *
 * int products_from[]: the list of products on the left side of each edges
 *
 * int products_to[]: the list of products on the right side of each edge
 *
 * Output
 * int: the minimum product sum or -1 if no triples exist.
 *
 * Constraints
 * There is always at least two products and one edge. All edges are valid, ie. they do not refer to itself, and are a valid product number, ie. between 1 and product_nodes.
 *
 * Examples
 * Example 1:
 * Input:
 * products_nodes = 6
 *
 * products_edges = 6
 *
 * products_from = [1, 2, 2, 3, 4, 5]
 *
 * products_to = [2, 4, 5, 5, 5, 6]
 *
 * Product	Related Products
 * 1	2
 * 2	1,4,5
 * 3	5
 * 4	2,5
 * 5	2,3,4,6
 * 6	5
 * A graph of n = 6 products where the only triple of related products is (2, 4, 5).
 *
 * Product	Outside Products	Which Products Are Outside
 * 2	1	1
 * 4	0
 * 5	2	3, 6
 * In the diagram above, the total product score is 1 + 0 + 2 = 3 for the triple (2, 4, 5).
 *
 * Output: 3
 * Example 2:
 * Input:
 * products_nodes = 5
 *
 * products_edges = 6
 *
 * products_from = [1, 1, 2, 2, 3, 4]
 *
 * products_to = [2, 3, 3, 4, 4, 5]
 *
 * Output: 2
 * Explanation:
 * There are two possible triples: {1,2,3} and {2,3,4}
 *
 * The score for {1,2,3} is 0 + 1 + 1 = 2.
 *
 * The score for {2,3,4} is 1 + 1 + 1 = 3.
 *
 * Return 2.
 */
public class ShoppingPatterns {


}
