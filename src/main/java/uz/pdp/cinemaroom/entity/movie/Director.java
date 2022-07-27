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
@Entity(name = "directors")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Director extends AbsEntity {

    private String firstName;
    private String lastName;


    @OneToOne(cascade = CascadeType.REMOVE)
    private Attachment attachment;
}
