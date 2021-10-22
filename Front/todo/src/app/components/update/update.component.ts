import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { TodoService } from "src/app/services/todo.service";
import { Todo } from "../models/todo";

@Component({
  selector: "app-update",
  templateUrl: "./update.component.html",
  styleUrls: ["./update.component.css"],
})
export class UpdateComponent implements OnInit {
  constructor(
    private router: Router,
    private todoService: TodoService,
    private route: ActivatedRoute
  ) {}

  todo: Todo = {
    titulo: "",
    descricao: "",
    dataFinalizar: new Date(),
    finalizado: false,
  };
  ngOnInit(): void {
    this.todo.id = this.route.snapshot.paramMap.get("id")!;
    this.findById();
  }

  update(): void {
    this.todoService.uptade(this.todo).subscribe(
      (resposta) => {
        this.todoService.message("Informacoes atualizadas com sucesso !!");
        this.router.navigate([""]);
      },
      (error) => {
        this.todoService.message("Falha ao atualizar informacoes");
        this.router.navigate([""]);
      }
    );
  }

  findById(): void {
    this.todoService.findById(this.todo.id).subscribe((resposta) => {
      this.todo = resposta;
    });
  }

  cancel(): void {
    this.router.navigate([""]);
  }

  formataData(): void {
    let data = new Date(this.todo.dataFinalizar);
    this.todo.dataFinalizar = `${data.getDate()}/${
      data.getMonth() + 1
    }/${data.getFullYear()}`;
  }
}
