package interviews.amazon.OA2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * An arborist that operates a plant store in Brooklyn, NY would like to improve their online sales by improving their ratings.
 * <p>
 * In order to become a five-star store, they must maintain a certain threshold percentage of five-star ratings. Given their current situation, find the minimum number of additional five-star ratings they must receive to meet the threshold. The overall online store percentage is calculated by taking the sum of percentages of five-star ratings for each product.
 * <p>
 * Examples
 * Example 1:
 * Input:
 * productCount = 3
 * <p>
 * productRatings = [[4,4],[1,2],[3,6]] where each entry is [five-star reviews, total reviews]
 * <p>
 * threshold = 77
 * <p>
 * Output: 3
 * Explanation :
 * We need the sum of the percentages of five-star ratings for each product to add up to the threshold.
 * <p>
 * The current percentage of five-star ratings for this seller is ((4/4) + (1/2) + (3/6))/3 = 66.66%
 * <p>
 * If we add a five star review to product #2, their threshold becomes ((4/4) + (2/3) + (3/6))/3 = 72.22%
 * <p>
 * If we add another five star review to product #2, their threshold becomes ((4/4) + (3/4) + (3/6))/3 = 75%
 * <p>
 * If we add a five star review to product #3, their threshold becomes ((4/4) + (3/4) + (4/7))/3 = 77.38%
 * <p>
 * At this point, the 77% threshold is met. The answer is 3, because there is no other way to add less ratings and achieve 77% or more.
 */
public class FiveStarSeller {

    public static void main(String[] args) {
        List<List<Integer>> ratings = new ArrayList<>();
        List<Integer> p1 = Arrays.asList(4, 4);
        List<Integer> p2 = Arrays.asList(1, 2);
        List<Integer> p3 = Arrays.asList(3, 6);
        ratings.add(p1);
        ratings.add(p2);
        ratings.add(p3);

        int res = fiveStarReviews(ratings, 77);
        System.out.println(res);
    }

    public static int fiveStarReviews(List<List<Integer>> ratings, int threshold) {

        /*
         * Choose the product where the diff between its current ratings vs its prev ratings would be highest
         * */
        PriorityQueue<ProductRating> queue = new PriorityQueue<>((a, b) -> Double.compare(b.getDiffRatingIfIncreased(), a.getDiffRatingIfIncreased()));

        double curThreshold;
        double ratingSum = 0;
        int count = 0;
        for (List<Integer> productRating : ratings) {
            int fiveStar = productRating.get(0);
            int totalStar = productRating.get(1);
            ProductRating pRating = new ProductRating(fiveStar, totalStar);
            queue.offer(pRating);
            System.out.println(pRating.getDiffRatingIfIncreased());
            ratingSum = ratingSum + ((double) fiveStar / (double) totalStar);
        } //added the seeds into queue

        curThreshold = (ratingSum / ratings.size());

        while (curThreshold < (threshold / 100.0)) {
            ProductRating nextValidProductToIncrease = queue.poll();
            curThreshold = curThreshold + (nextValidProductToIncrease.getDiffRatingIfIncreased() / ratings.size());
            nextValidProductToIncrease.increaseRatings();
            queue.offer(nextValidProductToIncrease);
            count++;
        }

        return count;
    }

    static class ProductRating {
        int fiveStars;
        int totalStars;

        public ProductRating(int fiveStars, int totalStars) {
            if (totalStars != 0) {
                this.fiveStars = fiveStars;
                this.totalStars = totalStars;
            }
        }

        public void increaseRatings() {
            this.fiveStars += 1;
            this.totalStars += 1;
        }

        public double getDiffRatingIfIncreased() {
            return (double) (fiveStars + 1) / (totalStars + 1) - (double) fiveStars / totalStars;
        }
    }
}
