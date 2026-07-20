import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.*;

public class Assign {
    
    public static float Assign(int n, int e, int m, float [][] prob, ArrayList<Integer> result) {
    	float[][] dp = new float[n + 1][e + 1];
    	int[][] choice = new int[n + 1][e + 1];

    	for (int i = 0; i <= n; i++) {
    		Arrays.fill(dp[i], -1.0f); 
    	}
    	dp[0][0] = 1.0f; 


    	for (int i = 0; i < n; i++) { 
    		for (int j = i; j <= e; j++) { 
    			if (dp[i][j] >= 0) { 
    				for (int k = 1; k <= m; k++) { 
    					if (j + k <= e) { 
    						float newProb = dp[i][j] * prob[i][k - 1];
    						if (newProb > dp[i + 1][j + k]) {
    							dp[i + 1][j + k] = newProb;
    							choice[i + 1][j + k] = k; 
    						}
    					}
    				}
    			}
    		}
    	}

    	float maxProb = -1.0f;
    	int optimalEnergy = -1;
    	for (int j = n; j <= e; j++) { 
    		if (dp[n][j] > maxProb) {
    			maxProb = dp[n][j];
    			optimalEnergy = j;
    		}
    	}

        if (optimalEnergy == -1) {
            for (int i = 0; i < n; i++) {
                result.add(1);
            }
            return 0.0f; 
        }

        int[] allocation = new int[n];
        int currentEnergy = optimalEnergy;
        for (int i = n; i > 0; i--) {
            int energyAllocated = choice[i][currentEnergy];
            allocation[i - 1] = energyAllocated;
            currentEnergy -= energyAllocated;
        }

        result.clear(); 
        for (int energy : allocation) {
            result.add(energy);
        }

        if (maxProb < 0) return 0.0f;

        return maxProb;
    }

    public static float Assign2(int n, int e, int m, float[][] prob, ArrayList<Integer> result) {
        float[][][] dp = new float[n + 1][e + 1][3]; 
        int[][][] choice = new int[n + 1][e + 1][3];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= e; j++) {
                Arrays.fill(dp[i][j], -1.0f);
            }
        }
        dp[0][0][0] = 1.0f; 

        for (int i = 0; i < n; i++) { 
            for (int j = i; j <= e; j++) { 
                for (int d = 0; d <= 2; d++) { 
                    if (dp[i][j][d] >= 0) { 
                        for (int k = 1; k <= m; k++) { 

                            int energyCost1 = k;
                            int newTotalEnergy1 = j + energyCost1;
                            if (newTotalEnergy1 <= e) {
                                float currentProb = prob[i][k - 1];
                                float newProb = dp[i][j][d] * currentProb;

                                if (newProb > dp[i + 1][newTotalEnergy1][d]) {
                                    dp[i + 1][newTotalEnergy1][d] = newProb;
                                    choice[i + 1][newTotalEnergy1][d] = k; 
                                }
                            }

                            int energyCost2 = k + 1; 
                            int newTotalEnergy2 = j + energyCost2;
                            int newDeviceCount = d + 1;

                            if (newDeviceCount <= 2 && newTotalEnergy2 <= e) {
                                float originalProb = prob[i][k - 1];
                                float boostedProb = Math.min(originalProb + 0.2f, Math.max(0.999f, originalProb));
                                float newProb = dp[i][j][d] * boostedProb;

                                if (newProb > dp[i + 1][newTotalEnergy2][newDeviceCount]) {
                                    dp[i + 1][newTotalEnergy2][newDeviceCount] = newProb;
                                    choice[i + 1][newTotalEnergy2][newDeviceCount] = -k; 
                                }
                            }
                        }
                    }
                }
            }
        }

        float maxProb = -1.0f;
        int optimalEnergy = -1;
        int optimalDevices = -1;

        for (int j = n; j <= e; j++) {
            for (int d = 0; d <= 2; d++) {
                if (dp[n][j][d] > maxProb) {
                    maxProb = dp[n][j][d];
                    optimalEnergy = j;
                    optimalDevices = d;
                }
            }
        }

        if (optimalEnergy == -1) {
            for (int i = 0; i < n; i++) {
                result.add(1); 
            }
             return 0.0f;
        }


        int[] allocation = new int[n];
        int currentEnergy = optimalEnergy;
        int currentDevices = optimalDevices;

        for (int i = n; i > 0; i--) {
            int energyChoice = choice[i][currentEnergy][currentDevices];
            allocation[i - 1] = energyChoice;

            if (energyChoice > 0) {
                currentEnergy -= energyChoice; 
            } else { 
                currentEnergy -= (-energyChoice + 1); 
                currentDevices -= 1; 
            }
            if (currentEnergy < 0 || currentDevices < 0) {
                 System.err.println("Error during backtracking: negative energy or number of devices.");
                 result.clear();
                 for(int vehicle = 0; vehicle < n; vehicle++) result.add(1); 
                 return 0.0f;
            }
        }

        result.clear();
        for (int energy : allocation) {
            result.add(energy);
        }

        if (maxProb < 0) return 0.0f;

        return maxProb;
    }
}

