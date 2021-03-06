package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import static viceCity.common.ExceptionMessages.*;

public abstract class BasePlayer implements Player{

    protected String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints){
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException (PLAYER_NULL_USERNAME);
        } else {
            this.name = name;
        }
        if (lifePoints < 1){
            throw new IllegalArgumentException(PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        } else {
            this.lifePoints = lifePoints;
        }
        this.gunRepository= new GunRepository();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        this.lifePoints-= points;
        if(lifePoints<=0){
            lifePoints=0;
        }
    }
}
