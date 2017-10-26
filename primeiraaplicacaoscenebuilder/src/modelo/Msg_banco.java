package modelo;

public class Msg_banco {
	private int Id;
	private String msg;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
		if (Id == 0) {
			this.setMsg("Banco de dados nao conectado");
		} else {
			if (Id == 1) {
				this.setMsg("Operação efetuada");
			} else {
				if (Id == 2) {
					this.setMsg("Operação nao efetuada");
				} else {
					if (Id == 3) {
						this.setMsg("Registro ja cadastrado");
					} else {
						if (Id == 4) {
							this.setMsg("Registro nao cadastrado");
						}
						if (Id == 5) {
							this.setMsg("Há um Banco cadastrado neste tipo, sendo assim nao é possivel exclui-lo");
						}
					}
				}
			}
		}
	}

	public String getInclusao() {
		return "Inclusao: " + msg;
	}

	public String getExclusao() {
		return "Exclusao: " + msg;
	}

	public String getAlteracao() {
		return "Alteracao: " + msg;
	}

	private void setMsg(String msg) {
		this.msg = msg;
	}

}
