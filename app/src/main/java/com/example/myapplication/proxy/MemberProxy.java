package com.example.myapplication.proxy;

public class MemberProxy implements TeamMamber {

    public MemberProxy(TeamMamber teamMamber) {
        if(teamMamber instanceof TeamMamberA){
            this.teamMamber = teamMamber;
        }
    }

    @Override
    public void reviewCode() {
        if(teamMamber!=null){
            teamMamber.reviewCode();
        }
    }

    TeamMamber teamMamber;

}
