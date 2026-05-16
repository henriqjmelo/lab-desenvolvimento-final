Gerar apresentação (slides)

Este repositório inclui um script simples que gera um arquivo PowerPoint com um resumo do projeto.

Requisitos

- Python 3.8+
- pip

Instalação rápida

```bash
cd sistema
python3 -m venv .venv
source .venv/bin/activate
pip install --upgrade pip
pip install python-pptx cairosvg pillow
```

Gerar os slides

```bash
python tools/generate_presentation.py
```

Saída

- `presentation/ru_presentation.pptx`

Observações

- O script tenta converter `src/main/resources/static/images/logo.svg` para `imagens/logo.png` usando `cairosvg`.
- Se a conversão não for possível, coloque manualmente um PNG em `imagens/logo.png` antes de rodar.
- Você pode editar o conteúdo do script para incluir screenshots adicionais (coloque imagens em `imagens/` e modifique o script para inseri-las).
