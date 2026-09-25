package com.finance.controller;

import com.finance.model.GoalContribution;
import com.finance.service.GoalContributionService;

public class GoalContributionController {

    private GoalContributionService contributionService =
            new GoalContributionService();

    public boolean addContribution(GoalContribution contribution) {
        return contributionService.addContribution(contribution);
    }
}