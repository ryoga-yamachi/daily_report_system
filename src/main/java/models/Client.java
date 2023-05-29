package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 日報データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CLI)
@NamedQueries({
        @NamedQuery(name = JpaConst.Q_CLI_GET_ALL, query = JpaConst.Q_CLI_GET_ALL_DEF),
        @NamedQuery(name = JpaConst.Q_CLI_COUNT, query = JpaConst.Q_CLI_COUNT_DEF),
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity

public class Client {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CLI_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 企業名（担当）
     */
    @Column(name = JpaConst.CLI_COL_COMPANY, nullable = false)
    private String company;

    /**
     * 内容
     */
    @Column(name = JpaConst.CLI_COL_TITLE, length = 255, nullable = false)
    private String title;

    /**
     * 状況
     */
    @Column(name = JpaConst.CLI_COL_PROGRESS, nullable = false)
    private Integer progress;

    /**
     * 経過
     */
    @Lob
    @Column(name = JpaConst.CLI_COL_CONTENT, nullable = false)
    private String content;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.CLI_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CLI_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 担当責任者
     */
    @Column(name = JpaConst.CLI_COL_EMPLOYEE, nullable = false)
    private String employee;

}
