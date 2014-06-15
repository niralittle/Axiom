package axiom.entity;

/**
 *
 * @author user
 */
public class UserSkill {

    public UserSkill(int userId, int skillId, String proof) {
        this.userId = userId;
        this.skillId = skillId;
        this.proof = proof;
    }
    
    public UserSkill() {
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    int userId;
    int skillId;
    String proof;
}
