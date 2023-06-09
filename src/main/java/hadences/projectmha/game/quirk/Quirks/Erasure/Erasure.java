package hadences.projectmha.game.quirk.Quirks.Erasure;

import hadences.projectmha.ProjectMHA;
import hadences.projectmha.game.quirk.QuirkCastManager;
import hadences.projectmha.utils.Damage;
import hadences.projectmha.utils.RayTrace;
import hadences.projectmha.utils.RaycastUtils;
import hadences.projectmha.utils.VectorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static hadences.projectmha.player.PlayerManager.FixQuirkSchedulers;
import static hadences.projectmha.player.PlayerManager.playerdata;

public class Erasure extends QuirkCastManager {
    ProjectMHA mha = ProjectMHA.getPlugin(ProjectMHA.class);

    public Ability1 ability1 = new Ability1();
    public Ability2 ability2 = new Ability2();
    public Ultimate ultimate = new Ultimate();
    public RCAbility rcAbility = new RCAbility();


    private String ABILITY1_DISPLAY_NAME;
    private String ABILITY1_DESCRIPTION;
    private double ABILITY1_DAMAGE;
    private Integer ABILITY1_STAMINACOST;
    private Integer ABILITY1_COOLDOWN;

    private String ABILITY2_DISPLAY_NAME;
    private String ABILITY2_DESCRIPTION;
    private double ABILITY2_DAMAGE;
    private Integer ABILITY2_STAMINACOST;
    private Integer ABILITY2_COOLDOWN;

    private String ULT_DISPLAY_NAME;
    private String ULT_DESCRIPTION;
    private double ULT_DAMAGE;
    private Integer ULT_STAMINACOST;
    private Integer ULT_COOLDOWN;

    private String RCABILITY_DISPLAY_NAME;
    private String RCABILITY_DESCRIPTION;
    private double RCABILITY_DAMAGE;
    private Integer RCABILITY_STAMINACOST;
    private Integer RCABILITY_COOLDOWN;

    private String QuirkName = "Erasure";

    private double OG_ABILITY1_DAMAGE;
    private double OG_ABILITY2_DAMAGE;
    private double OG_ULTIMATE_DAMAGE;
    private double OG_RCABILITY_DAMAGE;

    public double getOG_ABILITY1_DAMAGE() {
        return OG_ABILITY1_DAMAGE;
    }
    public double getOG_ABILITY2_DAMAGE() {
        return OG_ABILITY2_DAMAGE;
    }
    public double getOG_ULTIMATE_DAMAGE() {
        return OG_ULTIMATE_DAMAGE;
    }
    public double getOG_RCABILITY_DAMAGE() {
        return OG_RCABILITY_DAMAGE;
    }

    public Erasure() {
        ABILITY1_DISPLAY_NAME = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability1.Name");
        ABILITY1_DESCRIPTION = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability1.Description");
        ABILITY1_DAMAGE =  2 * (double) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability1.Damage");
        ABILITY1_STAMINACOST = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability1.StaminaCost");
        ABILITY1_COOLDOWN = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability1.Cooldown");

        ABILITY2_DISPLAY_NAME = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability2.Name");
        ABILITY2_DESCRIPTION = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability2.Description");
        ABILITY2_DAMAGE = 2 * (double) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability2.Damage");
        ABILITY2_STAMINACOST = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability2.StaminaCost");
        ABILITY2_COOLDOWN = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ability2.Cooldown");

        ULT_DISPLAY_NAME = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ultimate.Name");
        ULT_DESCRIPTION = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ultimate.Description");
        ULT_DAMAGE = 2 * (double) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ultimate.Damage");
        ULT_STAMINACOST = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ultimate.StaminaCost");
        ULT_COOLDOWN = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.Ultimate.Cooldown");

        RCABILITY_DISPLAY_NAME = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.RCAbility.Name");
        RCABILITY_DESCRIPTION = (String) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.RCAbility.Description");
        RCABILITY_DAMAGE = 2 * (double) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.RCAbility.Damage");
        RCABILITY_STAMINACOST = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.RCAbility.StaminaCost");
        RCABILITY_COOLDOWN = (Integer) mha.getConfig().get("Quirks." + QuirkName + ".Abilities.RCAbility.Cooldown");

        OG_ABILITY1_DAMAGE = ABILITY1_DAMAGE;
        OG_ABILITY2_DAMAGE = ABILITY2_DAMAGE;
        OG_ULTIMATE_DAMAGE = ULT_DAMAGE;
        OG_RCABILITY_DAMAGE = RCABILITY_DAMAGE;
    }


    public boolean CastAbility1(Player p) {
        ability1.ability(p);
        return true;
    }

    public boolean CastAbility2(Player p) {
        ability2.ability(p);
        return true;
    }

    public boolean CastUltimate(Player p) {
        broadcastUltimate(p);
        ultimate.ability(p);

        return true;
    }

    public boolean CastRCAbility(Player p) {
        return rcAbility.ability(p);

    }

    public String getABILITY1_DISPLAY_NAME() {
        return ABILITY1_DISPLAY_NAME;
    }

    public void setABILITY1_DISPLAY_NAME(String ABILITY1_DISPLAY_NAME) {
        this.ABILITY1_DISPLAY_NAME = ABILITY1_DISPLAY_NAME;
    }

    public String getABILITY1_DESCRIPTION() {
        return ABILITY1_DESCRIPTION;
    }

    public void setABILITY1_DESCRIPTION(String ABILITY1_DESCRIPTION) {
        this.ABILITY1_DESCRIPTION = ABILITY1_DESCRIPTION;
    }

    public double getABILITY1_DAMAGE() {
        return ABILITY1_DAMAGE;
    }

    public void setABILITY1_DAMAGE(double ABILITY1_DAMAGE) {
        this.ABILITY1_DAMAGE = ABILITY1_DAMAGE;
    }

    public Integer getABILITY1_STAMINACOST() {
        return ABILITY1_STAMINACOST;
    }

    public void setABILITY1_STAMINACOST(Integer ABILITY1_STAMINACOST) {
        this.ABILITY1_STAMINACOST = ABILITY1_STAMINACOST;
    }

    public Integer getABILITY1_COOLDOWN() {
        return ABILITY1_COOLDOWN;
    }

    public void setABILITY1_COOLDOWN(Integer ABILITY1_COOLDOWN) {
        this.ABILITY1_COOLDOWN = ABILITY1_COOLDOWN;
    }

    public String getABILITY2_DISPLAY_NAME() {
        return ABILITY2_DISPLAY_NAME;
    }

    public void setABILITY2_DISPLAY_NAME(String ABILITY2_DISPLAY_NAME) {
        this.ABILITY2_DISPLAY_NAME = ABILITY2_DISPLAY_NAME;
    }

    public String getABILITY2_DESCRIPTION() {
        return ABILITY2_DESCRIPTION;
    }

    public void setABILITY2_DESCRIPTION(String ABILITY2_DESCRIPTION) {
        this.ABILITY2_DESCRIPTION = ABILITY2_DESCRIPTION;
    }

    public double getABILITY2_DAMAGE() {
        return ABILITY2_DAMAGE;
    }

    public void setABILITY2_DAMAGE(double ABILITY2_DAMAGE) {
        this.ABILITY2_DAMAGE = ABILITY2_DAMAGE;
    }

    public Integer getABILITY2_STAMINACOST() {
        return ABILITY2_STAMINACOST;
    }

    public void setABILITY2_STAMINACOST(Integer ABILITY2_STAMINACOST) {
        this.ABILITY2_STAMINACOST = ABILITY2_STAMINACOST;
    }

    public Integer getABILITY2_COOLDOWN() {
        return ABILITY2_COOLDOWN;
    }

    public void setABILITY2_COOLDOWN(Integer ABILITY2_COOLDOWN) {
        this.ABILITY2_COOLDOWN = ABILITY2_COOLDOWN;
    }

    public String getULT_DISPLAY_NAME() {
        return ULT_DISPLAY_NAME;
    }

    public void setULT_DISPLAY_NAME(String ULT_DISPLAY_NAME) {
        this.ULT_DISPLAY_NAME = ULT_DISPLAY_NAME;
    }

    public String getULT_DESCRIPTION() {
        return ULT_DESCRIPTION;
    }

    public void setULT_DESCRIPTION(String ULT_DESCRIPTION) {
        this.ULT_DESCRIPTION = ULT_DESCRIPTION;
    }

    public double getULT_DAMAGE() {
        return ULT_DAMAGE;
    }

    public void setULT_DAMAGE(double ULT_DAMAGE) {
        this.ULT_DAMAGE = ULT_DAMAGE;
    }

    public Integer getULT_STAMINACOST() {
        return ULT_STAMINACOST;
    }

    public void setULT_STAMINACOST(Integer ULT_STAMINACOST) {
        this.ULT_STAMINACOST = ULT_STAMINACOST;
    }

    public Integer getULT_COOLDOWN() {
        return ULT_COOLDOWN;
    }

    public void setULT_COOLDOWN(Integer ULT_COOLDOWN) {
        this.ULT_COOLDOWN = ULT_COOLDOWN;
    }

    public String getRCABILITY_DISPLAY_NAME() {
        return RCABILITY_DISPLAY_NAME;
    }

    public void setRCABILITY_DISPLAY_NAME(String RCABILITY_DISPLAY_NAME) {
        this.RCABILITY_DISPLAY_NAME = RCABILITY_DISPLAY_NAME;
    }

    public String getRCABILITY_DESCRIPTION() {
        return RCABILITY_DESCRIPTION;
    }

    public void setRCABILITY_DESCRIPTION(String RCABILITY_DESCRIPTION) {
        this.RCABILITY_DESCRIPTION = RCABILITY_DESCRIPTION;
    }

    public double getRCABILITY_DAMAGE() {
        return RCABILITY_DAMAGE;
    }

    public void setRCABILITY_DAMAGE(double RCABILITY_DAMAGE) {
        this.RCABILITY_DAMAGE = RCABILITY_DAMAGE;
    }

    public Integer getRCABILITY_STAMINACOST() {
        return RCABILITY_STAMINACOST;
    }

    public void setRCABILITY_STAMINACOST(Integer RCABILITY_STAMINACOST) {
        this.RCABILITY_STAMINACOST = RCABILITY_STAMINACOST;
    }

    public Integer getRCABILITY_COOLDOWN() {
        return RCABILITY_COOLDOWN;
    }

    public void setRCABILITY_COOLDOWN(Integer RCABILITY_COOLDOWN) {
        this.RCABILITY_COOLDOWN = RCABILITY_COOLDOWN;
    }

    public String getQuirkName() {
        return QuirkName;
    }

    public void setQuirkName(String quirkName) {
        QuirkName = quirkName;
    }
}

class Ability1{
    private Location location;
    private Vector vector;
    private RaycastUtils raycastUtils;
    public Damage damage;

    public void ability(Player p){
        location = raycastUtils.StartRaycast(p,3.5,1);
        damage(p,playerdata.get(p.getUniqueId()).getQUIRK().getQUIRKCASTMANAGER().getABILITY1_DAMAGE());
        visuals(p);
        playsound(p);
    }

    public void damage(Player p, double dmg){
        damage = new Damage();
        damage.damageList(p,(ArrayList<Entity>) location.getNearbyEntities(1,1,1),dmg);
    }

    public void visuals(Player p){
        location = p.getEyeLocation();
        for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 25) {
            vector = VectorUtils.rotateVector(VectorUtils.rotateVector(new Vector(0.3 * Math.sin(theta), 1.6, 0.3 * Math.cos(theta)), -90, 90), p.getLocation().getYaw(), p.getLocation().getPitch());
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(vector), 5, 0.02, 0.02, 0.02, 0.02, new Particle.DustOptions(Color.fromRGB(255,255,255), 0.5f));
            vector = VectorUtils.rotateVector(VectorUtils.rotateVector(new Vector(0.6 * Math.sin(theta), 0.5, 0.6 * Math.cos(theta)), -90, 90), p.getLocation().getYaw(), p.getLocation().getPitch());
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(vector), 5, 0.02, 0.02, 0.02, 0.02, new Particle.DustOptions(Color.fromRGB(255,255,255), 0.5f));
        }
        vector = VectorUtils.rotateVector(VectorUtils.rotateVector(new Vector(0, 2, 0), -90, 90), p.getLocation().getYaw(), p.getLocation().getPitch());
        location.getWorld().spawnParticle(Particle.CLOUD,location.clone().add(vector),50,0,0,0,0.3);

    }

    public void playsound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT,1,1);
    }
}

class Ability2{
    Location loc;
    Location endpoint;
    Vector pos;

    double hitbox = (double) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Erasure.Abilities.Ability2.Erasure_Hitbox");

    public void ability(Player p){
        Damage damage = new Damage();
        loc = p.getEyeLocation();
        endpoint = RaycastUtils.StartRaycast(p,50,hitbox);
        List<Entity> TargetList = (List<Entity>) endpoint.getNearbyEntities(hitbox,hitbox,hitbox);
        TargetList = damage.cleanTargetList(p,TargetList);
        playsound(p);
        visuals(p);
        damage(p,TargetList);
    }

    public void damage(Player p, List<Entity> TargetList){
        if(TargetList.isEmpty()) return;
        for(Entity entity : TargetList){
            if(entity instanceof Player){
                //player
                if(p.getUniqueId() != entity.getUniqueId()){
                    p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER,2,1);
                    QuirkErased((Player) entity,p);
                }
            }else if((!(entity instanceof Item))){
                //not a player
                return;
            }
        }
   }

    public void visuals(Player p){
        for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 25) {
            pos = VectorUtils.rotateVector(VectorUtils.rotateVector(new Vector(0.3 * Math.sin(theta), 0.5, 0.3 * Math.cos(theta)), -90, 90), p.getLocation().getYaw(), p.getLocation().getPitch());
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(pos), 5, 0.02, 0.02, 0.02, 0.02, new Particle.DustOptions(Color.fromRGB(255,0,0), 0.5f));
        }
    }

    public void playsound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL,1,2);

    }

    public void QuirkErased(Player e, Player p){
        playerdata.get(e.getUniqueId()).setABILITY_USAGE(false);
        e.sendTitle(ChatColor.RED + "QUIRK ERASED","");
        BukkitTask ErasureTask = new ErasureScheduler(e,ProjectMHA.getPlugin(ProjectMHA.class)).runTaskLater(ProjectMHA.getPlugin(ProjectMHA.class), 20* (int) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Erasure.Abilities.Ability2.EraseTimer"));
        FixQuirkSchedulers(e,playerdata.get(p.getUniqueId()).getQUIRK().getQUIRK_NAME(),ErasureTask);
    }
}

class Ultimate {

    Location loc;
    Vector pos;
    List<Entity> target;
    double hitbox = (double) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Erasure.Abilities.Ultimate.Erasure_Hitbox");

    public void ability(Player p){
        playSound(p);
        visuals(p);
        erase(p);
    }

    public void visuals(Player p){
        loc = p.getEyeLocation();
        for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 42) {
            pos = VectorUtils.rotateVector(VectorUtils.rotateVector(new Vector(0.5 * Math.sin(theta), 0.5, 0.5 * Math.cos(theta)), -90, 90), p.getLocation().getYaw(), p.getLocation().getPitch());
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(pos), 5, 0.02, 0.02, 0.02, 0.02, new Particle.DustOptions(Color.fromRGB(255,0,0), 0.5f));
        }
    }

    public void erase(Player p){
        RayTrace rayTrace = new RayTrace(p.getEyeLocation().toVector(),p.getEyeLocation().getDirection());
        ArrayList<Vector> positions = rayTrace.traverse(hitbox,0.01);
        loc = positions.get(positions.size()-1).toLocation(p.getWorld());
        target = (List<Entity>) loc.getNearbyEntities(hitbox,hitbox,hitbox);
        Damage damage = new Damage();
        target = damage.cleanTargetList(p,target);
        for(Entity e: target){
            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER,2,1);
            if(e instanceof Player){
                QuirkErased((Player) e,p);
            }
        }
    }

    public void playSound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS,2,2);

    }

    public void QuirkErased(Player e, Player p){
        playerdata.get(e.getUniqueId()).setABILITY_USAGE(false);
        e.sendTitle(ChatColor.RED + "QUIRK ERASED","");
        BukkitTask Erase = new ErasureScheduler(e,ProjectMHA.getPlugin(ProjectMHA.class)).runTaskLater(ProjectMHA.getPlugin(ProjectMHA.class), 20* (int) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Erasure.Abilities.Ultimate.ErasureTimer"));
        FixQuirkSchedulers(e,playerdata.get(p.getUniqueId()).getQUIRK().getQUIRK_NAME()+"Ultimate",Erase);
    }
}

class RCAbility {

    int length = (int) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Blackwhip.Abilities.Ability1.Blackwhip_Length");
    double hitbox = (double) ProjectMHA.getPlugin(ProjectMHA.class).getConfig().get("Quirks.Blackwhip.Abilities.Ability1.Blackwhip_Hitbox");

    private Location loc;
    private Location endpoint;

    public boolean ability(Player p) {
        loc = p.getLocation();
        endpoint = RaycastUtils.StartRaycast(p,length,hitbox);
        return pulllogic(p,endpoint);
    }

    public boolean pulllogic(Player p, Location endpoint) {
        double length_from_origin = RaycastUtils.calculateDistance(loc,endpoint);

        if (endpoint.getBlock().isSolid() && endpoint.getBlock().getType() != Material.BARRIER) {
            playsound(p);
            ErasureAbilityBlock(endpoint,p);
            visuals(p,length_from_origin,0,0);
        }else{
            p.playSound(p.getLocation(),Sound.BLOCK_ANVIL_LAND,1,1);
            return false;
        }
        return true;
    }

    public void visuals(Player p, double length, float yaw, float pitch){
        Vector pos;
        loc = p.getEyeLocation();
        double y = -0.1;
        for(double t = 0; t <= length; t += 0.1){
            pos = VectorUtils.rotateVector(new Vector(t,y,0),loc.getYaw()+yaw,loc.getPitch()+pitch);
            //White Particle
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(pos), 10, 0.08, 0.08, 0.08, 0,new Particle.DustOptions(Color.fromRGB(255,255,255), 0.5F));
        }
    }

    public void playsound(Player p){
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0f,2.0f);
    }

    public void ErasureAbilityBlock(Location block, Player p){
        new BukkitRunnable(){
            Location playerLoc = p.getLocation();
            Vector v;
            int time = 0;
            @Override
            public void run() {
                if(block.distance(playerLoc) < 4 || time > 80){
                    p.setFallDistance(0.0f);
                    this.cancel();
                }
                playerLoc = p.getLocation();
                v = block.toVector().subtract(playerLoc.toVector());
                p.setVelocity(v.normalize().multiply(1));
                time++;
            }
        }.runTaskTimer(ProjectMHA.getPlugin(ProjectMHA.class),0,0);
    }

}

class ErasureScheduler extends BukkitRunnable{

    Player e;
    ProjectMHA plugin;
    public ErasureScheduler(Player e, ProjectMHA plugin){
        this.e = e;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        playerdata.get(e.getUniqueId()).setABILITY_USAGE(true);
    }
}
