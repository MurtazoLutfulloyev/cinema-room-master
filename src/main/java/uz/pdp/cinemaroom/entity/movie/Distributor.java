package uz.pdp.cinemaroom.entity.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.attachment.Attachment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "distributors")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Distributor extends AbsEntity {

    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Attachment attachment;


}
