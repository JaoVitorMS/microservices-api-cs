build:
	docker compose --progress=plain build --parallel

up:
	docker compose up -d --no-build

down:
	docker compose down

rebuild:
	docker compose --progress=plain build --parallel
	docker compose up -d --no-build

logs:
	docker compose logs -f --tail=200