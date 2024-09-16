-- Script de inserção de labels no banco de dados com as chaves de tradução
INSERT INTO system_labels (label_name, created_at, update_at) VALUES
                            ('label.favoritos', NOW(), NOW()),
                            ('label.spam', NOW(), NOW()),
                            ('label.fiap', NOW(), NOW()),
                            ('label.alura', NOW(), NOW()),
                            ('label.vagas', NOW(), NOW()),
                            ('label.interesses', NOW(), NOW()),
                            ('label.lixeira', NOW(), NOW()),
                            ('label.naoLido', NOW(), NOW());
