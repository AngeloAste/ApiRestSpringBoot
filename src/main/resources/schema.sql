CREATE TABLE IF NOT EXISTS call_history (
    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    endpoint VARCHAR(255) NOT NULL,
    request_params TEXT,
    response TEXT,
    status_code INT NOT NULL
);
