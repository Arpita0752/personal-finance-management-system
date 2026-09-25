package com.finance.service;

import com.finance.dao.GoalContributionDAO;
import com.finance.model.GoalContribution;

public class GoalContributionService {

    private GoalContributionDAO goalContributionDAO =
            new GoalContributionDAO();

    public boolean addContribution(GoalContribution contribution) {
        return goalContributionDAO.addContribution(contribution);
    }
}